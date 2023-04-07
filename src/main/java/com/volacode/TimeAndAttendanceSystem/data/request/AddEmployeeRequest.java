package com.volacode.TimeAndAttendanceSystem.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployeeRequest {
    private String email;
    private String password;
}
