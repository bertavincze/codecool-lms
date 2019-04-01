package com.codecool.model.user;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class DateCounter implements Serializable {

    private final LocalDate STARTDATE = LocalDate.of(2019, Month.MARCH, 1);

    public long getDifferenceInDays() {
        return ChronoUnit.DAYS.between(STARTDATE, LocalDate.now());
    }
}
