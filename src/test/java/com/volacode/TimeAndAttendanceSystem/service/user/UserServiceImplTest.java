package com.volacode.TimeAndAttendanceSystem.service.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.volacode.TimeAndAttendanceSystem.data.response.TAAUserResponse;
import com.volacode.TimeAndAttendanceSystem.exceptions.TAAException;
import com.volacode.TimeAndAttendanceSystem.exceptions.UserNotFoundException;
import com.volacode.TimeAndAttendanceSystem.models.Role;
import com.volacode.TimeAndAttendanceSystem.models.TAAUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class UserServiceImplTest {


    @Autowired
    private UserService userService;
    private TAAUser request;


    @BeforeEach
    void setup(){
        request =  TAAUser
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
    void testThatWeCanAddAnEmployee()throws TAAException{

        TAAUserResponse response =  userService.addEmployee(request);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getCode()).isEqualTo(201);
        assertThat(response.getId()).isGreaterThan(0L);

    }

    @Test
    void  testThatWeCanModifyEmployee()throws TAAException {

        ObjectMapper mapper = new ObjectMapper();
        TAAUserResponse modifiedResponse = userService.addEmployee(request);
        try{
            JsonNode value =mapper.readTree("\"Ope\"");
            JsonPatch patch = new JsonPatch(List.of(new ReplaceOperation(
                    new JsonPointer("/firstName"),value)));

            modifiedResponse = userService.modifyEmployee(1L, patch);
        }catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(modifiedResponse).isNotNull();
        assertThat(modifiedResponse.getCode()).isEqualTo(201);
        assertThat(userService.getEmployeeById(1L).getFirstName()).isEqualTo("Ope");

    }

    @Test
    void testThatWeCanGetEmployeeById() throws UserNotFoundException{
        TAAUserResponse response = userService.addEmployee(request);
        TAAUser foundEmployee = userService.getEmployeeById(response.getId());

            assertThat(foundEmployee).isNotNull();
            assertThat(foundEmployee.getId()).isEqualTo(response.getId());
    }
}