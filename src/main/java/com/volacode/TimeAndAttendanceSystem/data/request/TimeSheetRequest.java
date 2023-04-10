package com.volacode.TimeAndAttendanceSystem.data.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TimeSheetRequest {


    private LocalDateTime checkInTime;
    private long userId;
}
