package com.volacode.TimeAndAttendanceSystem.service.timesheet;

import com.volacode.TimeAndAttendanceSystem.data.request.TimeSheetRequest;
import com.volacode.TimeAndAttendanceSystem.exceptions.TimeSheetException;
import com.volacode.TimeAndAttendanceSystem.models.TimeStamp;
import com.volacode.TimeAndAttendanceSystem.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@RequiredArgsConstructor
class TimeSheetServiceTest {

    @Autowired
    private  TimeSheetService timeSheetService;
    private TimeSheetRequest timeSheetRequest;


    @BeforeEach
     void setUp() {

        timeSheetRequest = TimeSheetRequest
                .builder()
                .checkInTime(Utils.getCurrentTime())
                .userId(1)
                .build();
    }




    @Test
    void clockInTest() {
        String response = timeSheetService.clockIn(timeSheetRequest.getUserId());
        assertThat(response).isNotNull();
        assertThat(response.contains("clock in successful")).isTrue();

    }

    @Test
    void testThatWeCannotClockInTwice(){
        String response = timeSheetService.clockIn(timeSheetRequest.getUserId());
        assertThat(response).isNotNull();
        assertThat(response.contains("clock in successful")).isTrue();
        assertThatThrownBy(() -> timeSheetService.clockIn(1))
                .isInstanceOf(TimeSheetException.class)
                .hasMessageContaining("already clocked in for the day");


    }

    @Test
    void clockOutTest() {
      timeSheetService.clockIn(timeSheetRequest.getUserId());
        String response = timeSheetService.clockOut(timeSheetRequest);
        assertThat(response).isNotNull();
    }

    @Test
    void startBreakTest() {
        timeSheetService.clockIn(timeSheetRequest.getUserId());
        String response = timeSheetService.startBreak(timeSheetRequest);
        assertThat(response).isNotNull();

    }

    @Test
    void endBreakTest() {

        timeSheetService.clockIn(timeSheetRequest.getUserId());
        timeSheetService.startBreak(timeSheetRequest);

        String response = timeSheetService.endBreak(timeSheetRequest);
        assertThat(response).isNotNull();
    }
}