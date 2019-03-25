package com.codecool.model.user;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class DateCounter {

    float days;
    final String dayOne = "01 10 2018";

    DateCounter(){
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String dateAfterString = myFormat.format(new Date());


        try {
            Date dateBefore = myFormat.parse(dayOne);
            Date dateAfter = myFormat.parse(dateAfterString);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getDaysDifference(){
        return days;
    }
}
