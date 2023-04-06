package com.volacode.TimeAndAttendanceSystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.volacode.TimeAndAttendanceSystem.data.request.EmployeeRequest;
import com.volacode.TimeAndAttendanceSystem.data.response.AppUserResponse;
import com.volacode.TimeAndAttendanceSystem.exceptions.TimeSheetException;
import com.volacode.TimeAndAttendanceSystem.exceptions.UserNotFoundException;
import com.volacode.TimeAndAttendanceSystem.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AppUserController {


    private final UserService userService;


    @PostMapping("/add-employee")
    public ResponseEntity<?> addEmployee( @RequestBody EmployeeRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED).body(userService.addEmployee(request));
    }

    @PatchMapping("/modify-employee/{id}")
    public ResponseEntity<?> modifyEmployeeDetails(@PathVariable Long id, @RequestBody JsonPatch patch) {
        try {
            AppUserResponse modifyEmployee = userService.modifyEmployee(id, patch);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(modifyEmployee);
        } catch (JsonPatchException | UserNotFoundException | TimeSheetException | JsonProcessingException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
