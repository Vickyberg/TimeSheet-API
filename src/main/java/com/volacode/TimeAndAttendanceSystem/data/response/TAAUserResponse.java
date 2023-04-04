package com.volacode.TimeAndAttendanceSystem.data.response;



import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class TAAUserResponse {


    private Long id;
    private String email;
    private String message;
    private int code;


}
