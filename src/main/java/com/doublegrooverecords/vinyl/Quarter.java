package com.doublegrooverecords.vinyl;

import java.time.Month;
import java.time.ZonedDateTime;
import java.util.Date;

public interface Quarter {
    String getName();
    Date getStart();
    Date getEnd();

    default ZonedDateTime firstDayMonthInYearWithZone(ZonedDateTime clockDateTime, Month month) {
        return ZonedDateTime.of(clockDateTime.getYear(), month.getValue(), 1, 0, 0, 0, 0, clockDateTime.getZone());
    }

    Quarter previous();
}
