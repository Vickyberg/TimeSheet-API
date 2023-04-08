package com.volacode.TimeAndAttendanceSystem.service.timesheet;

import com.volacode.TimeAndAttendanceSystem.data.request.TimeSheetRequest;
import com.volacode.TimeAndAttendanceSystem.exceptions.TimeSheetException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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
                .userId(1)
                .checkInTime(LocalDateTime.now())
                .build();

    }


    @Test
    void clockInTest() {
        String response = timeSheetService.clockIn(timeSheetRequest.getUserId());
        assertThat(response).isNotNull();
        assertThat(response.contains("clock in successful")).isTrue();
        assertThatThrownBy(() -> timeSheetService.clockIn(1))
                .isInstanceOf(TimeSheetException.class)
                .hasMessageContaining("already clocked in for the day");

    }

    @Test
    void clockOutTest() {


    }

    @Test
    void startBreakTest() {
    }

    @Test
    void endBreakTest() {
    }
}