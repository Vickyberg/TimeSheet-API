package com.volacode.TimeAndAttendanceSystem.service.timesheet;

import com.volacode.TimeAndAttendanceSystem.data.request.TimeSheetRequest;
import com.volacode.TimeAndAttendanceSystem.exceptions.TimeSheetException;
import com.volacode.TimeAndAttendanceSystem.models.TimeStamp;
import com.volacode.TimeAndAttendanceSystem.repositories.TimeStampRepository;
import com.volacode.TimeAndAttendanceSystem.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class TimeSheetService {

    private final  int TIME_PERIOD =5;

    private final TimeStampRepository timestampsRepository;


    public String clockIn(long userID) {
        ensureNoRepeatedClockIn(userID);
        TimeStamp timeStamp = new TimeStamp(userID);

        timestampsRepository.save(timeStamp);
        return "clock in successful at : " + timeStamp.getCheckInTime();
    }

    private void ensureNoRepeatedClockIn(long userId) {
        for (TimeStamp timeStamp : timestampsRepository.findAll()) {
            if (timeStamp.getUserId() == userId) {
                if (LocalDateTime.now().isAfter(timeStamp.getCheckInTime()) &&
                        LocalDateTime.now().isBefore(timeStamp.getCheckInTime().plusMinutes(TIME_PERIOD))
                ) {

                    throw new TimeSheetException("already clocked in for the day");
                }
            }
        }

    }


    private void ensureNoRepeatedClockOut(TimeStamp timeStamp) {
        if (timeStamp.getCheckOutTime() != null) throw new TimeSheetException("already clocked out for the day");
    }

    public String clockOut(TimeSheetRequest timeSheetRequest) {
        TimeStamp timeStamp = getTimeSheet(timeSheetRequest);
        ensureNoRepeatedClockOut(timeStamp);
        timeStamp.setCheckOutTime(Utils.getCurrentTime());
        if (timeStamp.getBreakStart() != null && timeStamp.getBreakEnd() == null) {
            timeStamp.setBreakEnd(Utils.getCurrentTime());
        }
        calculateTotalWorkHours(timeStamp);
        return "clock out successfully at: " + timeStamp.getCheckOutTime();
    }

    private void calculateTotalWorkHours(TimeStamp timeStamp) {
        long hours = ChronoUnit.HOURS.between(timeStamp.getCheckInTime(), timeStamp.getCheckOutTime());
        long breakHours = 0;
        if (timeStamp.getBreakStart() != null) {
            breakHours = ChronoUnit.HOURS.between(timeStamp.getBreakStart(), timeStamp.getBreakEnd());
        }

        timeStamp.setTotalWorkHours((int) (hours - breakHours));
        timestampsRepository.save(timeStamp);
    }

    private TimeStamp getTimeSheet(TimeSheetRequest timeSheetRequest) {
        return timestampsRepository
                .findByCheckInTimeAndUserId(timeSheetRequest.getCheckInTime(), timeSheetRequest.getUserId())
                .orElseThrow(
                        () -> new TimeSheetException("invalid check in time or user id")
                );
    }

    public String startBreak(TimeSheetRequest timeSheetRequest) {
        TimeStamp timeStamp = getTimeSheet(timeSheetRequest);
        ensureUserHssNoTClockedOut(timeStamp);
        timeStamp.setBreakStart(Utils.getCurrentTime());
        timestampsRepository.save(timeStamp);
        return "break started";
    }

    private void ensureUserHssNoTClockedOut(TimeStamp timeStamp) {
        if (timeStamp.getCheckOutTime() != null)
            throw new TimeSheetException("already clocked out. cannot start break");

    }

    public String endBreak(TimeSheetRequest timeSheetRequest) {
        TimeStamp timeStamp = getTimeSheet(timeSheetRequest);
        ensureUserHasStartedBreak(timeStamp);
        timeStamp.setBreakEnd(Utils.getCurrentTime());
        timestampsRepository.save(timeStamp);
        return "break ended";
    }

    private void ensureUserHasStartedBreak(TimeStamp timeStamp) {
        ensureUserHssNoTClockedOut(timeStamp);
        if (timeStamp.getBreakStart() == null) {
            throw new TimeSheetException("start break first");
        }
    }
}
