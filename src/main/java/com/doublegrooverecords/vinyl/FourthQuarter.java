package com.doublegrooverecords.vinyl;

import java.time.Month;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

class FourthQuarter implements Quarter {
    private final Date startDate;
    private final Date endDate;
    private final ZonedDateTime clockDateTime;

    public FourthQuarter(ZonedDateTime clockDateTime) {
        this.startDate = Date.from(firstDayMonthInYearWithZone(clockDateTime, Month.OCTOBER).toInstant());
        this.endDate = Date.from(firstDayNextYearWithZone(clockDateTime).toInstant());
        this.clockDateTime = clockDateTime;
    }

    @Override
    public String getName() {
        return "Q4";
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
        return new ThirdQuarter(clockDateTime);
    }

    protected static ZonedDateTime firstDayNextYearWithZone(ZonedDateTime clockDateTime) {
        final int nextYear = clockDateTime.plus(1L, ChronoUnit.YEARS).getYear();
        return ZonedDateTime.of(nextYear, Month.JANUARY.getValue(), 1, 0, 0, 0, 0, clockDateTime.getZone());
    }
}
