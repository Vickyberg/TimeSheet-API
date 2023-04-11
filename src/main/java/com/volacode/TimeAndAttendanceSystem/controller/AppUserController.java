package com.volacode.TimeAndAttendanceSystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.volacode.TimeAndAttendanceSystem.data.request.AddEmployeeRequest;
import com.volacode.TimeAndAttendanceSystem.data.request.PaymentSlipRequest;
import com.volacode.TimeAndAttendanceSystem.data.response.AppUserResponse;
import com.volacode.TimeAndAttendanceSystem.exceptions.TimeSheetException;
import com.volacode.TimeAndAttendanceSystem.exceptions.UserNotFoundException;
import com.volacode.TimeAndAttendanceSystem.service.timesheet.TimeSheetServiceImp;
import com.volacode.TimeAndAttendanceSystem.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AppUserController {


    private final UserService userService;
    private final TimeSheetServiceImp timeSheetService;


    @PostMapping("/add_employee")
    public ResponseEntity<?> addEmployee( @RequestBody AddEmployeeRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED).body(userService.addEmployee(request));
    }

    @PatchMapping(path = "/modify/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> modifyEmployeeDetails(@PathVariable Long id, @RequestBody JsonPatch patch) {
        try {
            AppUserResponse modifyEmployee = userService.modifyEmployee(id, patch);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(modifyEmployee);
        } catch (JsonPatchException | UserNotFoundException | TimeSheetException | JsonProcessingException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/generate_payment_slip")
    public ResponseEntity<String> generatePaymentSlip(@RequestBody PaymentSlipRequest paymentSlipRequest) {
        return ResponseEntity.ok(timeSheetService.generatePaymentSlip(paymentSlipRequest));
    }

}
