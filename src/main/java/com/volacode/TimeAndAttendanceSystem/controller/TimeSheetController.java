package com.volacode.TimeAndAttendanceSystem.controller;

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
    public ResponseEntity<String> clockOut(@RequestBody TimeSheetRequest timeSheetRequest) {
        return ResponseEntity.ok(timeSheetService.clockOut(timeSheetRequest));
    }

    @PostMapping("/start-break")
    public ResponseEntity<String> startBreak(@RequestBody TimeSheetRequest timeSheetRequest) {
        return ResponseEntity.ok(timeSheetService.startBreak(timeSheetRequest));
    }

    @PostMapping("/end-break")
    public ResponseEntity<String> endBreak(@RequestBody TimeSheetRequest timeSheetRequest) {
        return ResponseEntity.ok(timeSheetService.endBreak(timeSheetRequest));
    }
}
