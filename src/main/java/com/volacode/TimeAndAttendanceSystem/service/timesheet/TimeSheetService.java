package com.volacode.TimeAndAttendanceSystem.service.timesheet;

import com.volacode.TimeAndAttendanceSystem.data.request.TimeSheetRequest;
import com.volacode.TimeAndAttendanceSystem.exceptions.TimeSheetException;
import com.volacode.TimeAndAttendanceSystem.models.TimeStamp;
import com.volacode.TimeAndAttendanceSystem.models.Type;
import com.volacode.TimeAndAttendanceSystem.repositories.TimeStampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSheetService {
//
//    private final TimeSheetRepository timeSheetRepository;
    private final TimeStampRepository timestampsRepository;

    public String clockIn(TimeSheetRequest timeSheetRequest) {
        ensureNoRepeatedClockIn(Type.CLOCK_IN);
        TimeStamp timeStamp = new TimeStamp(timeSheetRequest.getUserId());

        timestampsRepository.save(timeStamp);
        return "clock in successful";
    }

    private void ensureNoRepeatedClockIn(Type type) {
        for (TimeStamp timeStamp : timestampsRepository.findAll()) {

            if (LocalDateTime.now().isAfter(timeStamp.getCheckInTime()) &&
                    LocalDateTime.now().isBefore(timeStamp.getCheckInTime().plusMinutes(1))
            ) {
                String message = type == Type.CLOCK_IN ? "in" : "out";
                throw new TimeSheetException("already clocked " + message + " for the day");
            }
        }

    }

    public String clockOut(TimeSheetRequest timeSheetRequest) {
//        TimeSheet timeSheet = timeSheetRepository.findByUserId(timeSheetRequest.getUserId());
//        ensureNoRepeatedClockInOROut(timeSheet, Type.CLOCK_OUT);
//        List<TimeStamp> timeStamps = timestampsRepository
//                .findByTypeAndUserId(Type.CLOCK_IN, timeSheetRequest.getUserId());
//
//
//        TimeStamp foundTimeStamp = timeStamps.stream()
//                .filter(timeStamp -> LocalDateTime.now().isAfter(timeStamp.getTime()) &&
//                        LocalDateTime.now().isBefore(timeStamp.getTime().plusDays(1)))
//                .findFirst().orElseThrow(
//                        () -> new TimeSheetException("no clock in for the day")
//                );
//
//        foundTimeStamp.setCheckOutTime(LocalDateTime.now());
//        timestampsRepository.save(foundTimeStamp);
//        return "clock out successful";

        List<TimeStamp> timestamps = timestampsRepository
                .findByTypeAndUserId(Type.CLOCK_IN, timeSheetRequest.getUserId());

        TimeStamp foundTimeStamp =  timestamps.stream()
                .filter(timeStamp -> LocalDateTime.now().isAfter(timeStamp.getTime())
                        && LocalDateTime.now().isAfter(timeStamp.getTime().plusMinutes(1)))
                        .findFirst().orElseThrow(
                        () -> new TimeSheetException("You cannot clock out")
                );
        foundTimeStamp.setCheckOutTime(LocalDateTime.now());
        timestampsRepository.save(foundTimeStamp);
        return "clock out successfully";


    }

    public String startBreak(long userId) {
        List<TimeStamp> timeStamps = timestampsRepository
                .findByTypeAndUserId(Type.CLOCK_IN, userId);
        TimeStamp foundTimeStamp = timeStamps.stream()
                .filter(timeStamp -> LocalDateTime.now().isAfter(timeStamp.getTime()) && LocalDateTime.now().isBefore(timeStamp.getTime().plusDays(1)))
                .findFirst().orElseThrow(
                        () -> new TimeSheetException("no clock in for the day")
                );
        foundTimeStamp.setBreakStart(LocalDateTime.now());
        timestampsRepository.save(foundTimeStamp);
        return "break started";
    }

    public String endBreak(long userId) {
        List<TimeStamp> timeStamps = timestampsRepository
                .findByTypeAndUserId(Type.CLOCK_IN, userId);
        TimeStamp foundTimeStamp = timeStamps.stream()
                .filter(timeStamp -> LocalDateTime.now().isAfter(timeStamp.getTime()) && LocalDateTime.now().isBefore(timeStamp.getTime().plusDays(1)))
                .findFirst().orElseThrow(
                        () -> new TimeSheetException("no clock in for the day")
                );
        foundTimeStamp.setBreakEnd(LocalDateTime.now());
        timestampsRepository.save(foundTimeStamp);
        return "break stopped";
    }
}
