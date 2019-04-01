package com.codecool.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class DateUtilService {

    private final LocalDate COURSESTARTDATE = LocalDate.of(2019, Month.MARCH, 1);

    public long getNumOfDaysSinceStart() {
        return ChronoUnit.DAYS.between(COURSESTARTDATE, LocalDate.now());
    }
}
