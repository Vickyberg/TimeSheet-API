package com.volacode.TimeAndAttendanceSystem.repositories;

import com.volacode.TimeAndAttendanceSystem.models.TimeStamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TimeStampRepository  extends JpaRepository<TimeStamp, Long> {

    Optional<TimeStamp> findByCheckInTimeAndUserId(LocalDateTime localDateTime, long userId);

    List<TimeStamp> findByUserId(long userId);

}
