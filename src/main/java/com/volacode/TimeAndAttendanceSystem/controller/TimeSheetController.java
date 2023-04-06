package com.volacode.TimeAndAttendanceSystem.controller;

import com.volacode.TimeAndAttendanceSystem.data.request.ClockOutRequest;
import com.volacode.TimeAndAttendanceSystem.data.request.TimeSheetRequest;
import com.volacode.TimeAndAttendanceSystem.service.timesheet.TimeSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/timesheet")
public class TimeSheetController {
    private final TimeSheetService timeSheetService;

    @PostMapping("/clock-in/{userId}")
    public ResponseEntity<String> clockIn(@PathVariable long userId) {
        return ResponseEntity.ok(timeSheetService.clockIn(userId));
    }

    @PostMapping("/clock-out")
    public ResponseEntity<String> clockOut(@RequestBody ClockOutRequest clockOutRequest) {
        return ResponseEntity.ok(timeSheetService.clockOut(clockOutRequest));
    }

    @PostMapping("/start-break/{userId}")
    public ResponseEntity<String> startBreak(@PathVariable long userId) {
        return ResponseEntity.ok(timeSheetService.startBreak(userId));
    }

    @PostMapping("/end-break/{userId}")
    public ResponseEntity<String> endBreak(@PathVariable long userId) {
        return ResponseEntity.ok(timeSheetService.endBreak(userId));
    }
}
