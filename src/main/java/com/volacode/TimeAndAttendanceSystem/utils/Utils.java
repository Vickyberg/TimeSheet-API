package com.volacode.TimeAndAttendanceSystem.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static LocalDateTime getCurrentTime() {
        return LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
