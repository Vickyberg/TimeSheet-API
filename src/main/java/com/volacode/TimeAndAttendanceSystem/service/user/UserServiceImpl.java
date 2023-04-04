package com.volacode.TimeAndAttendanceSystem.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.volacode.TimeAndAttendanceSystem.data.response.TAAUserResponse;
import com.volacode.TimeAndAttendanceSystem.exceptions.TAAException;
import com.volacode.TimeAndAttendanceSystem.exceptions.UserNotFoundException;
import com.volacode.TimeAndAttendanceSystem.models.TAAUser;
import com.volacode.TimeAndAttendanceSystem.repositories.TAAUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements  UserService{

    private final ModelMapper mapper = new ModelMapper();
    private final TAAUserRepository userRepository;
    private ObjectMapper    objectMapper = new ObjectMapper();


    @Override
    public TAAUserResponse addEmployee(TAAUser request) {

        Optional<TAAUser> foundUser = userRepository.findByEmail(request.getEmail());
        if (foundUser.isPresent()) throw new TAAException(
            String.format("Email %s already exist", request.getEmail()));

        TAAUser user = mapper.map(request,TAAUser.class);
        TAAUser savedUser = userRepository.save(user);
        return addEmployeeBuilder(savedUser);
    }
    

    @Override
    public TAAUserResponse modifyEmployee(Long id, JsonPatch userPatch) throws JsonPatchException, JsonProcessingException {

        Optional<TAAUser> foundUser = userRepository.findById(id);
        if(foundUser.isEmpty()) throw new UserNotFoundException("user not found");

        TAAUser modifiedUser = applyPatchToUser(userPatch,foundUser);

        TAAUser savedUser = userRepository.save(modifiedUser);
        return buildModifiedUserResponse(savedUser);



    }

    @Override
    public TAAUser getEmployeeById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(
                        String.format("User with id %d not found",id)
                ));
    }

    private TAAUserResponse buildModifiedUserResponse(TAAUser savedUser) {

     return    TAAUserResponse.builder()
             .email(savedUser.getEmail())
             .message("Modified Successfully")
             .build();
    }


    private TAAUser applyPatchToUser(JsonPatch userPatch, Optional<TAAUser> targetUser) throws JsonPatchException , JsonProcessingException {

        var userNode = objectMapper.convertValue(targetUser,JsonNode.class);
        JsonNode patchedUserNode;
        try {
            patchedUserNode = userPatch.apply(userNode);
            var updatedUser = objectMapper.readValue(objectMapper.writeValueAsBytes(patchedUserNode),TAAUser.class);
        } catch (IOException   | JsonPatchException  exception) {
            exception.printStackTrace();
            return null;
        }

        return null;
    }


    private TAAUserResponse addEmployeeBuilder(TAAUser savedUser) {
      return   TAAUserResponse.builder()
                .message("Employee added successfully")
                .id(savedUser.getId())
                .code(201)
                .build();
    }
}
