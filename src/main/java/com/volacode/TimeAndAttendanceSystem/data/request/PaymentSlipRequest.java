package com.volacode.TimeAndAttendanceSystem.data.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PaymentSlipRequest {

    private long userId;
    private int year;
    private int month;
}
