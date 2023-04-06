package com.volacode.TimeAndAttendanceSystem.repositories;

import com.volacode.TimeAndAttendanceSystem.models.TimeStamp;
import com.volacode.TimeAndAttendanceSystem.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeStampRepository  extends JpaRepository<TimeStamp, Long> {



    List<TimeStamp> findByTypeAndUserId(Type type, long userId);
}
