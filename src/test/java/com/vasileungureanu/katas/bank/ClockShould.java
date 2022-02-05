package com.vasileungureanu.katas.bank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Clock should")
public class ClockShould {
    @Test
    @DisplayName("provide a formatted today's date")
    public void provideFormattedTodayDate() {
        Clock clock = new TestableClock();

        String date = clock.dateAsString();

        assertThat(date).isEqualTo("24/04/2015");
    }

    private static class TestableClock extends Clock {
        @Override
        protected LocalDate today() {
            return LocalDate.of(2015, 4, 24);
        }
    }
}