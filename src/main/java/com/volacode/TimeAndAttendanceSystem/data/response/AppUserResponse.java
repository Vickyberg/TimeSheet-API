package com.volacode.TimeAndAttendanceSystem.data.response;



import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class AppUserResponse {


    private long id;
    private String email;
    private String message;
    private int code;


}
