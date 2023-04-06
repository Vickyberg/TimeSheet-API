package com.volacode.TimeAndAttendanceSystem;

import com.volacode.TimeAndAttendanceSystem.data.APIError;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<APIError> handleException(Exception e) {
        return ResponseEntity.badRequest().body(APIError.builder()

                .message(e.getLocalizedMessage())
                .ok(false)
                .build());
    }
}
