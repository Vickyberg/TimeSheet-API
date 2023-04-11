package com.volacode.TimeAndAttendanceSystem.data.request;

import com.volacode.TimeAndAttendanceSystem.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ModifyUserRequest {

    private  String firstName;
    private String lastName;
    private String email;
    private Role role;
}
