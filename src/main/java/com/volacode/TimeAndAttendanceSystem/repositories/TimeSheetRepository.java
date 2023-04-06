package com.volacode.TimeAndAttendanceSystem.repositories;

import com.volacode.TimeAndAttendanceSystem.models.TimeSheet;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeSheetRepository extends JpaRepository<TimeSheet,Long> {

    TimeSheet findByUserId(long userId);
}
