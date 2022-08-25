package com.doublegrooverecords.vinyl;

import java.time.Month;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

class ThirdQuarter implements Quarter {
    private final Date startDate;
    private final Date endDate;
    private final ZonedDateTime clockDateTime;

    public ThirdQuarter(ZonedDateTime clockDateTime) {
        this.startDate = Date.from(firstDayMonthInYearWithZone(clockDateTime, Month.JULY).toInstant());
        this.endDate = Date.from(firstDayMonthInYearWithZone(clockDateTime, Month.OCTOBER).toInstant());
        this.clockDateTime = clockDateTime;
    }

    @Override
    public String getName() {
        return "Q3";
    }

    @Override
    public Date getStart() {
        return startDate;
    }

    @Override
    public Date getEnd() {
        return endDate;
    }

    @Override
    public Quarter previous() {
        return new SecondQuarter(clockDateTime);
    }

    public static List<Month> months() {
        return List.of(Month.JULY, Month.AUGUST, Month.SEPTEMBER);
    }
}
