package com.volacode.TimeAndAttendanceSystem.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@RequiredArgsConstructor
public class TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime time;
    private LocalDateTime checkInTime =  LocalDateTime.now();
    private LocalDateTime checkOutTime;

    private Type type;
    private LocalDateTime breakStart;
    private LocalDateTime breakEnd;
    private long userId;
    public TimeStamp(long userId){
        this.userId = userId;
    }


}
