package com.volacode.TimeAndAttendanceSystem.data.request;


import com.volacode.TimeAndAttendanceSystem.models.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserRequest {


    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
