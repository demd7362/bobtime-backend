package com.bobtime.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");

    public static String currentTimeAsString() {
        return toString(currentTime());
    }


    public static LocalTime currentTime() {
        return LocalTime.now();
    }

    public static String currentDateAsString() {
        return toString(currentDate());
    }

    public static String toString(TemporalAccessor date) {
        return DATE_FORMATTER.format(date);
    }

    public static LocalDate currentDate() {
        return LocalDate.now();
    }
    public static LocalDateTime current(){
        return LocalDateTime.now();
    }
    public static String currentAsString(){
        return toString(current());
    }
}
