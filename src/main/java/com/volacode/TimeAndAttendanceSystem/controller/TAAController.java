package com.volacode.TimeAndAttendanceSystem.controller;

import com.volacode.TimeAndAttendanceSystem.models.TAAUser;
import com.volacode.TimeAndAttendanceSystem.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TAAController {


    private final UserService userService;

    @PostMapping("/admin/addEmployee")
    public ResponseEntity<?> addEmployee( @RequestBody TAAUser request){
        return  ResponseEntity.status(HttpStatus.CREATED).body(userService.addEmployee(request));
    }




}
