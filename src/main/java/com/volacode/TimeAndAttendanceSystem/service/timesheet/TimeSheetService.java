package com.volacode.TimeAndAttendanceSystem.service.timesheet;

import com.volacode.TimeAndAttendanceSystem.data.request.PaymentSlipRequest;
import com.volacode.TimeAndAttendanceSystem.data.request.TimeSheetRequest;

public interface TimeSheetService {

    String clockIn(long id);
    String clockOut(TimeSheetRequest timeSheetRequest);
    String startBreak(TimeSheetRequest timeSheetRequest);
    String endBreak(TimeSheetRequest timeSheetRequest);
    String generatePaymentSlip(PaymentSlipRequest paymentSlipRequest);
}
