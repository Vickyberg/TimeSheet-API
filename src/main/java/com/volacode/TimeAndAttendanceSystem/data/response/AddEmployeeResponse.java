package com.volacode.TimeAndAttendanceSystem.data.response;


import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class AddEmployeeResponse {

    @Id
    private Long id;
    private String message;
    private int code;


}
