package com.volacode.TimeAndAttendanceSystem.data.request;

import com.volacode.TimeAndAttendanceSystem.models.Gender;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ModifyEmployeeRequest {
    private Long id;
    private Gender gender;
    private String state;
    private String city;
    private String address;
    private String phoneNumber;


}
