package com.volacode.TimeAndAttendanceSystem.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.volacode.TimeAndAttendanceSystem.data.request.AddEmployeeRequest;
import com.volacode.TimeAndAttendanceSystem.data.response.AppUserResponse;
import com.volacode.TimeAndAttendanceSystem.exceptions.TimeSheetException;
import com.volacode.TimeAndAttendanceSystem.exceptions.UserNotFoundException;
import com.volacode.TimeAndAttendanceSystem.models.AppUser;
import com.volacode.TimeAndAttendanceSystem.models.Role;
import com.volacode.TimeAndAttendanceSystem.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final ModelMapper mapper = new ModelMapper();
    private final AppUserRepository userRepository;
    private ObjectMapper objectMapper = new ObjectMapper();
        private final PasswordEncoder passwordEncoder;
    @Override
    public AppUserResponse addEmployee(AddEmployeeRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new TimeSheetException("User already exists");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        AppUser savedUser = mapper.map(request, AppUser.class);
        userRepository.save(savedUser);
        return addUserBuilder(savedUser);
    }



    @Override
    public AppUserResponse modifyEmployee(Long id, JsonPatch userPatch) throws JsonPatchException, JsonProcessingException {

        var foundUser = userRepository.findById(id);
        if (foundUser.isEmpty()) throw new UserNotFoundException("user not found");

        AppUser modifiedUser = applyPatchToUser(userPatch, foundUser);

        AppUser savedUser = userRepository.save(modifiedUser);
        return buildModifiedUserResponse(savedUser);


    }

    @Override
    public AppUser getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(
                        String.format("User with id %d not found", id)));
    }

    @Override
    public AppUser findUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("user not found")
        );
    }

    private AppUserResponse buildModifiedUserResponse(AppUser savedUser) {

        return AppUserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .message("Modified Successfully")
                .code(201)
                .build();
    }


    private AppUser applyPatchToUser(JsonPatch userPatch, Optional<AppUser> targetUser) throws JsonPatchException, JsonProcessingException {

        JsonNode userNode = objectMapper.convertValue(targetUser, JsonNode.class);
        JsonNode patchedUserNode;
        try {
            patchedUserNode = userPatch.apply(userNode);
            var updatedUser = objectMapper.readValue(objectMapper.writeValueAsBytes(patchedUserNode), AppUser.class);
            return updatedUser;
        } catch (IOException | JsonPatchException exception) {
            exception.printStackTrace();
            return null;
        }
    }


    private AppUserResponse addUserBuilder(AppUser savedUser) {
        return AppUserResponse.builder()
                .message("Employee added successfully")
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .code(201)
                .build();
    }


}
