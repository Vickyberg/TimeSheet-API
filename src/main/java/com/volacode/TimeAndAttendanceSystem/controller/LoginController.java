package com.volacode.TimeAndAttendanceSystem.controller;

import com.volacode.TimeAndAttendanceSystem.security.service.AuthRequest;
import com.volacode.TimeAndAttendanceSystem.security.service.AuthResonse;
import com.volacode.TimeAndAttendanceSystem.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class LoginController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResonse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

}
