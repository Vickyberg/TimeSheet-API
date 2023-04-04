package com.volacode.TimeAndAttendanceSystem.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.volacode.TimeAndAttendanceSystem.data.response.TAAUserResponse;
import com.volacode.TimeAndAttendanceSystem.models.TAAUser;

public interface UserService {

   TAAUserResponse addEmployee(TAAUser request);

   TAAUserResponse modifyEmployee(Long id, JsonPatch userPatch) throws JsonPatchException, JsonProcessingException;

   TAAUser getEmployeeById(Long id);

}
