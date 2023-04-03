package com.volacode.TimeAndAttendanceSystem.service.user;

import com.volacode.TimeAndAttendanceSystem.data.request.AddEmployeeRequest;
import com.volacode.TimeAndAttendanceSystem.data.response.AddEmployeeResponse;
import com.volacode.TimeAndAttendanceSystem.exceptions.TAAException;
import com.volacode.TimeAndAttendanceSystem.models.TAAUser;
import com.volacode.TimeAndAttendanceSystem.repositories.TAAUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements  UserService{

    private final ModelMapper mapper = new ModelMapper();
    private final TAAUserRepository repository;


    @Override
    public AddEmployeeResponse addEmployee(AddEmployeeRequest request) {

        Optional<TAAUser> foundUser = repository.findByEmail(request.getEmail());
        if (foundUser.isPresent()) throw new TAAException(
            String.format("Email %s already exist", request.getEmail()));

        TAAUser user = mapper.map(request,TAAUser.class);
        TAAUser savedUser = repository.save(user);

        return addEmployeeBuilder(savedUser);
    }


    private AddEmployeeResponse addEmployeeBuilder(TAAUser savedUser) {
      return   AddEmployeeResponse.builder()
                .message("Employee added successfully")
                .id(savedUser.getId())
                .code(201)
                .build();
    }
}
