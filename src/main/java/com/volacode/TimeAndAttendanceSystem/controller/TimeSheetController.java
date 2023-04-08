package com.volacode.TimeAndAttendanceSystem.controller;

import com.volacode.TimeAndAttendanceSystem.data.request.PaymentSlipRequest;
import com.volacode.TimeAndAttendanceSystem.data.request.TimeSheetRequest;
import com.volacode.TimeAndAttendanceSystem.service.timesheet.TimeSheetServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/timesheet")
public class TimeSheetController {
    private final TimeSheetServiceImp timeSheetService;

    @PostMapping("/clock_in/{userId}")
    public ResponseEntity<String> clockIn(@PathVariable long userId) {
        return ResponseEntity.ok(timeSheetService.clockIn(userId));
    }

    @PostMapping("/clock_out")
    public ResponseEntity<String> clockOut(@RequestBody TimeSheetRequest timeSheetRequest) {
        return ResponseEntity.ok(timeSheetService.clockOut(timeSheetRequest));
    }

    @PostMapping("/start_break")
    public ResponseEntity<String> startBreak(@RequestBody TimeSheetRequest timeSheetRequest) {
        return ResponseEntity.ok(timeSheetService.startBreak(timeSheetRequest));
    }

    @PostMapping("/end_break")
    public ResponseEntity<String> endBreak(@RequestBody TimeSheetRequest timeSheetRequest) {
        return ResponseEntity.ok(timeSheetService.endBreak(timeSheetRequest));
    }

    @PostMapping("/generate_payment_slip")
    public ResponseEntity<String> generatePaymentSlip(@RequestBody PaymentSlipRequest paymentSlipRequest) {
        return ResponseEntity.ok(timeSheetService.generatePaymentSlip(paymentSlipRequest));
    }
}
