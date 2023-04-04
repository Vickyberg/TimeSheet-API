package com.volacode.TimeAndAttendanceSystem.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public static String NotFoundException(String email) {
        return "User with email " + email + " not found";
    }
}
