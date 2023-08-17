package com.volacode.TimeAndAttendanceSystem.data.request;

import com.volacode.TimeAndAttendanceSystem.models.Role;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddEmployeeRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}
