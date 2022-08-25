package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class QuarterTest {
    @Test
    void returnsQuarterOne_whenMonthIsJanuary() {
        final Instant jan5th1985 = Instant.parse("1985-01-05T01:30:00Z");
        final Clock clock = Clock.fixed(jan5th1985, ZoneId.of("UTC"));

        Quarter quarter = QuarterFactory.make(clock);
        assertThat(quarter.getName()).isEqualTo("Q1");
        assertThat(quarter.getStart()).isEqualTo("1985-01-01T00:00:00Z");
        assertThat(quarter.getEnd()).isEqualTo("1985-04-01T00:00:00Z");

        assertFourthQuarterOfPreviousYear(quarter.previous());
    }

    private void assertFourthQuarterOfPreviousYear(Quarter previous) {
        assertThat(previous.getName()).isEqualTo("Q4");
        assertThat(previous.getStart()).isEqualTo("1984-10-01T00:00:00Z");
        assertThat(previous.getEnd()).isEqualTo("1985-01-01T00:00:00Z");
    }

    @Test
    void returnsQuarterOne_whenMonthIsFebruary() {
        final Instant feb5th1985 = Instant.parse("1985-02-05T01:30:00Z");
        final Clock clock = Clock.fixed(feb5th1985, ZoneId.of("UTC"));

        Quarter quarter = QuarterFactory.make(clock);
        assertThat(quarter.getName()).isEqualTo("Q1");
        assertThat(quarter.getStart()).isEqualTo("1985-01-01T00:00:00Z");
        assertThat(quarter.getEnd()).isEqualTo("1985-04-01T00:00:00Z");

        assertFourthQuarterOfPreviousYear(quarter.previous());
    }

    @Test
    void returnsQuarterOne_whenMonthIsMarch() {
        final Instant march5th1985 = Instant.parse("1985-03-05T01:30:00Z");
        final Clock clock = Clock.fixed(march5th1985, ZoneId.of("UTC"));

        Quarter quarter = QuarterFactory.make(clock);
        assertThat(quarter.getName()).isEqualTo("Q1");
        assertThat(quarter.getStart()).isEqualTo("1985-01-01T00:00:00Z");
        assertThat(quarter.getEnd()).isEqualTo("1985-04-01T00:00:00Z");

        assertFourthQuarterOfPreviousYear(quarter.previous());
    }

    @Test
    void returnsSecondQuarter_whenMonthIsApril() {
        final Instant april5th1985 = Instant.parse("1985-04-05T01:30:00Z");
        final Clock clock = Clock.fixed(april5th1985, ZoneId.of("UTC"));

        Quarter quarter = QuarterFactory.make(clock);
        assertThat(quarter.getName()).isEqualTo("Q2");
        assertThat(quarter.getStart()).isEqualTo("1985-04-01T00:00:00Z");
        assertThat(quarter.getEnd()).isEqualTo("1985-07-01T00:00:00Z");
        assertThat(quarter.previous()).isInstanceOf(FirstQuarter.class);
    }

    @Test
    void returnsSecondQuarter_whenMonthIsMay() {
        final Instant may5th1985 = Instant.parse("1985-05-05T01:30:00Z");
        final Clock clock = Clock.fixed(may5th1985, ZoneId.of("UTC"));

        Quarter quarter = QuarterFactory.make(clock);
        assertThat(quarter.getName()).isEqualTo("Q2");
        assertThat(quarter.getStart()).isEqualTo("1985-04-01T00:00:00Z");
        assertThat(quarter.getEnd()).isEqualTo("1985-07-01T00:00:00Z");
        assertThat(quarter.previous()).isInstanceOf(FirstQuarter.class);
    }

    @Test
    void returnsSecondQuarter_whenMonthIsJune() {
        final Instant june5th1985 = Instant.parse("1985-06-05T01:30:00Z");
        final Clock clock = Clock.fixed(june5th1985, ZoneId.of("UTC"));

        Quarter quarter = QuarterFactory.make(clock);
        assertThat(quarter.getName()).isEqualTo("Q2");
        assertThat(quarter.getStart()).isEqualTo("1985-04-01T00:00:00Z");
        assertThat(quarter.getEnd()).isEqualTo("1985-07-01T00:00:00Z");
        assertThat(quarter.previous()).isInstanceOf(FirstQuarter.class);
    }

    @Test
    void returnsThirdQuarter_whenMonthIsJuly() {
        final Instant july5th1985 = Instant.parse("1985-07-05T01:30:00Z");
        final Clock clock = Clock.fixed(july5th1985, ZoneId.of("UTC"));

        Quarter quarter = QuarterFactory.make(clock);
        assertThat(quarter.getName()).isEqualTo("Q3");
        assertThat(quarter.getStart()).isEqualTo("1985-07-01T00:00:00Z");
        assertThat(quarter.getEnd()).isEqualTo("1985-10-01T00:00:00Z");
        assertThat(quarter.previous()).isInstanceOf(SecondQuarter.class);
    }

    @Test
    void returnsThirdQuarter_whenMonthIsAugust() {
        final Instant august5th1985 = Instant.parse("1985-08-05T01:30:00Z");
        final Clock clock = Clock.fixed(august5th1985, ZoneId.of("UTC"));

        Quarter quarter = QuarterFactory.make(clock);
        assertThat(quarter.getName()).isEqualTo("Q3");
        assertThat(quarter.getStart()).isEqualTo("1985-07-01T00:00:00Z");
        assertThat(quarter.getEnd()).isEqualTo("1985-10-01T00:00:00Z");
        assertThat(quarter.previous()).isInstanceOf(SecondQuarter.class);
    }

    @Test
    void returnsThirdQuarter_whenMonthIsSeptember() {
        final Instant sept5th1985 = Instant.parse("1985-09-05T01:30:00Z");
        final Clock clock = Clock.fixed(sept5th1985, ZoneId.of("UTC"));

        Quarter quarter = QuarterFactory.make(clock);
        assertThat(quarter.getName()).isEqualTo("Q3");
        assertThat(quarter.getStart()).isEqualTo("1985-07-01T00:00:00Z");
        assertThat(quarter.getEnd()).isEqualTo("1985-10-01T00:00:00Z");
        assertThat(quarter.previous()).isInstanceOf(SecondQuarter.class);
    }

    @Test
    void returnsFourthQuarter_whenMonthIsOctober() {
        final Instant oct5th1985 = Instant.parse("1985-10-05T01:30:00Z");
        final Clock clock = Clock.fixed(oct5th1985, ZoneId.of("UTC"));

        Quarter quarter = QuarterFactory.make(clock);
        assertThat(quarter.getName()).isEqualTo("Q4");
        assertThat(quarter.getStart()).isEqualTo("1985-10-01T00:00:00Z");
        assertThat(quarter.getEnd()).isEqualTo("1986-01-01T00:00:00Z");
        assertThat(quarter.previous()).isInstanceOf(ThirdQuarter.class);
    }

    @Test
    void returnsFourthQuarter_whenMonthIsNovember() {
        final Instant nov5th1985 = Instant.parse("1985-11-05T01:30:00Z");
        final Clock clock = Clock.fixed(nov5th1985, ZoneId.of("UTC"));

        Quarter quarter = QuarterFactory.make(clock);
        assertThat(quarter.getName()).isEqualTo("Q4");
        assertThat(quarter.getStart()).isEqualTo("1985-10-01T00:00:00Z");
        assertThat(quarter.getEnd()).isEqualTo("1986-01-01T00:00:00Z");
        assertThat(quarter.previous()).isInstanceOf(ThirdQuarter.class);
    }

    @Test
    void returnsFourthQuarter_whenMonthIsDecember() {
        final Instant dec5th1985 = Instant.parse("1985-12-05T01:30:00Z");
        final Clock clock = Clock.fixed(dec5th1985, ZoneId.of("UTC"));

        Quarter quarter = QuarterFactory.make(clock);
        assertThat(quarter.getName()).isEqualTo("Q4");
        assertThat(quarter.getStart()).isEqualTo("1985-10-01T00:00:00Z");
        assertThat(quarter.getEnd()).isEqualTo("1986-01-01T00:00:00Z");
        assertThat(quarter.previous()).isInstanceOf(ThirdQuarter.class);
    }
}