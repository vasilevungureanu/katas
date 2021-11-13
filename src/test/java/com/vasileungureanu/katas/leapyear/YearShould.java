package com.vasileungureanu.katas.leapyear;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Year should")
public class YearShould {
    @Test
    @DisplayName("not be a leap year if is not divisible by 4")
    public void
    notBeLeapYearIfNotDivisibleBy4() {
        assertThat(leapYear(1997)).isFalse();
    }

    @Test
    @DisplayName("be a leap year if is divisible by 4")
    public void
    beLeapYearIfDivisibleBy4() {
        assertThat(leapYear(1996)).isTrue();
    }

    @Test
    @DisplayName("be a leap year if is divisible by 400")
    public void
    beLeapYearIfDivisibleBy400() {
        assertThat(leapYear(1600)).isTrue();
    }

    @Test
    @DisplayName("not be a leap year if is divisible by 100 but not by 400")
    public void
    notBeLeapYearIfDivisibleBy100ButNotBy400() {
        assertThat(leapYear(1800)).isFalse();
    }

    private boolean leapYear(int year) {
        return new Year(year).isLeapYear();
    }
}