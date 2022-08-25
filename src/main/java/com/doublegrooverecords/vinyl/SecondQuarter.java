package com.doublegrooverecords.vinyl;

import java.time.Month;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

class SecondQuarter implements Quarter {
    private final Date startDate;
    private final Date endDate;
    private final ZonedDateTime clockDateTime;

    public SecondQuarter(ZonedDateTime clockDateTime) {
        this.startDate = Date.from(firstDayMonthInYearWithZone(clockDateTime, Month.APRIL).toInstant());
        this.endDate = Date.from(firstDayMonthInYearWithZone(clockDateTime, Month.JULY).toInstant());
        this.clockDateTime = clockDateTime;
    }

    @Override
    public String getName() {
        return "Q2";
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
        return new FirstQuarter(clockDateTime);
    }

    public static List<Month> months() {
        return List.of(Month.APRIL, Month.MAY, Month.JUNE);
    }
}
