package com.volacode.TimeAndAttendanceSystem.service.user;

import com.volacode.TimeAndAttendanceSystem.data.request.AddEmployeeRequest;
import com.volacode.TimeAndAttendanceSystem.data.response.AddEmployeeResponse;
import com.volacode.TimeAndAttendanceSystem.models.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceImplTest {


    @Autowired
    private UserService userService;
    private AddEmployeeRequest request;

    @BeforeEach
    void setup(){
        request =  AddEmployeeRequest
                .builder()
                .firstName("Victor")
                .lastName("Olamide")
                .email("olamide@gmail.com")
                .role(Role.USER)
                .build();
    }
    @AfterEach
    void tearDown(){}

    @Test
    void testThatWeCanAddAnEmployee(){

        AddEmployeeResponse employeeResponse = userService.addEmployee(request);
        assertThat(employeeResponse).isNotNull();
        assertThat(employeeResponse.getMessage()).isNotNull();
        assertThat(employeeResponse.getCode()).isEqualTo(201);
        assertThat(employeeResponse.getId()).isGreaterThan(0);

    }
}