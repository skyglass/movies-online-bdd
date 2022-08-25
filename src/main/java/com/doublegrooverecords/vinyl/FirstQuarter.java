package com.doublegrooverecords.vinyl;

import java.time.Month;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

class FirstQuarter implements Quarter {
    private final Date startDate;
    private final Date endDate;
    private final ZonedDateTime clockDateTime;

    public FirstQuarter(ZonedDateTime clockDateTime) {
        this.startDate = Date.from(firstDayMonthInYearWithZone(clockDateTime, Month.JANUARY).toInstant());
        this.endDate = Date.from(firstDayMonthInYearWithZone(clockDateTime, Month.APRIL).toInstant());
        this.clockDateTime = clockDateTime;
    }

    @Override
    public String getName() {
        return "Q1";
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
        final ZonedDateTime lastYear = this.clockDateTime.withYear(clockDateTime.getYear() - 1);
        return new FourthQuarter(lastYear);
    }

    public static List<Month> months() {
        return List.of(Month.JANUARY, Month.FEBRUARY, Month.MARCH);
    }
}
