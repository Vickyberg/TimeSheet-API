package com.volacode.TimeAndAttendanceSystem.security.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthResonse {
    private String token;

}
