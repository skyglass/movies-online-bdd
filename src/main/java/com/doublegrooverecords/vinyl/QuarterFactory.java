package com.doublegrooverecords.vinyl;

import java.time.Clock;
import java.time.ZonedDateTime;

public final class QuarterFactory {
    public static Quarter make(Clock clock) {
        ZonedDateTime clockDateTime = ZonedDateTime.now(clock);
        if (FirstQuarter.months().contains(clockDateTime.getMonth())) {
            return new FirstQuarter(clockDateTime);
        } else if (SecondQuarter.months().contains(clockDateTime.getMonth())) {
            return new SecondQuarter(clockDateTime);
        } else if (ThirdQuarter.months().contains(clockDateTime.getMonth())) {
            return new ThirdQuarter(clockDateTime);
        }
        return new FourthQuarter(clockDateTime);
    }
}