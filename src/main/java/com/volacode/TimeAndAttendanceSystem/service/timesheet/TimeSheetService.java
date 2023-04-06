package com.volacode.TimeAndAttendanceSystem.service.timesheet;

import com.volacode.TimeAndAttendanceSystem.data.request.ClockOutRequest;
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

    private final  int TIME_PERIOD =2;

//    private final TimeSheetRepository timeSheetRepository;
    private final TimeStampRepository timestampsRepository;

    public String clockIn(long userId) {
        ensureNoRepeatedClockIn(userId);
        TimeStamp timeStamp = new TimeStamp(userId);

        timestampsRepository.save(timeStamp);
        return "clock in successful at " + timeStamp.getCheckInTime();
    }

    private void ensureNoRepeatedClockIn(long userId) {
        for (TimeStamp timeStamp : timestampsRepository.findAll()) {

            if (LocalDateTime.now().isAfter(timeStamp.getCheckInTime()) &&
                    LocalDateTime.now().isBefore(timeStamp.getCheckInTime().plusMinutes(TIME_PERIOD))
            ) {
                throw new TimeSheetException("already clocked in for the day");
            }
        }

    }

    public String clockOut(ClockOutRequest clockOutRequest) {

        TimeStamp timeStamp = timestampsRepository
                .findByCheckInTimeAndUserId(clockOutRequest.getCheckInTime(), clockOutRequest.getUserId())
                .orElseThrow(
                        () -> new TimeSheetException("invalid check in time or user id")
                );
        ensureNoRepeatedClockOut(timeStamp);
        timeStamp.setCheckOutTime(LocalDateTime.now());
        timestampsRepository.save(timeStamp);
        return "clock out successfully at: " + timeStamp.getCheckOutTime();
    }

    private void ensureNoRepeatedClockOut(TimeStamp timeStamp) {
        if (timeStamp.getCheckOutTime() != null) throw new TimeSheetException("already clocked out for the day");
    }

    public String startBreak(long userId) {
//        List<TimeStamp> timeStamps = timestampsRepository
//                .findByTypeAndUserId(Type.CLOCK_IN, userId);
//        TimeStamp foundTimeStamp = timeStamps.stream()
//                .filter(timeStamp -> LocalDateTime.now().isAfter(timeStamp.getTime()) && LocalDateTime.now().isBefore(timeStamp.getTime().plusDays(1)))
//                .findFirst().orElseThrow(
//                        () -> new TimeSheetException("no clock in for the day")
//                );
//        foundTimeStamp.setBreakStart(LocalDateTime.now());
//        timestampsRepository.save(foundTimeStamp);
        return "break started";
    }

    public String endBreak(long userId) {
//        List<TimeStamp> timeStamps = timestampsRepository
//                .findByTypeAndUserId(Type.CLOCK_IN, userId);
//        TimeStamp foundTimeStamp = timeStamps.stream()
//                .filter(timeStamp -> LocalDateTime.now().isAfter(timeStamp.getTime()) && LocalDateTime.now().isBefore(timeStamp.getTime().plusDays(1)))
//                .findFirst().orElseThrow(
//                        () -> new TimeSheetException("no clock in for the day")
//                );
//        foundTimeStamp.setBreakEnd(LocalDateTime.now());
//        timestampsRepository.save(foundTimeStamp);
  return "break stopped";
    }
}
