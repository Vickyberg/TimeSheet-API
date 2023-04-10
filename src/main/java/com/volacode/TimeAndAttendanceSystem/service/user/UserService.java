package com.volacode.TimeAndAttendanceSystem.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.volacode.TimeAndAttendanceSystem.data.request.AddEmployeeRequest;
import com.volacode.TimeAndAttendanceSystem.data.request.PaymentSlipRequest;
import com.volacode.TimeAndAttendanceSystem.data.response.AppUserResponse;
import com.volacode.TimeAndAttendanceSystem.models.AppUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {


AppUserResponse addEmployee(AddEmployeeRequest request);


   AppUserResponse modifyEmployee(Long id, JsonPatch userPatch) throws JsonPatchException, JsonProcessingException;

   AppUser getUserById(Long id);


   AppUser findUserByEmail(String email) throws UsernameNotFoundException;






}
