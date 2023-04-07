package com.volacode.TimeAndAttendanceSystem.data.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeSheetRequest {


    private LocalDateTime checkInTime;
    private long userId;
}
