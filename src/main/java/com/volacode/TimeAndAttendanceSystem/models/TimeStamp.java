package com.volacode.TimeAndAttendanceSystem.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.volacode.TimeAndAttendanceSystem.utils.Utils;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@RequiredArgsConstructor
public class TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  int totalWorkHours;

    private LocalDateTime checkInTime = Utils.getCurrentTime();
    private LocalDateTime checkOutTime;
    private LocalDateTime breakStart;
    private LocalDateTime breakEnd;
    private long userId;
    public TimeStamp(long userId){
        this.userId = userId;
    }


}
