package com.volacode.TimeAndAttendanceSystem.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.NonNullApi;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String email;
    private String password;
}
