package com.volacode.TimeAndAttendanceSystem.service.user;

import com.volacode.TimeAndAttendanceSystem.data.request.AddEmployeeRequest;
import com.volacode.TimeAndAttendanceSystem.data.response.AddEmployeeResponse;

public interface UserService {

   AddEmployeeResponse addEmployee(AddEmployeeRequest request);
}
