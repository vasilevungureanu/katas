package com.vasileungureanu.katas.bankwithoutmockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Clock should")
public class ClockShould {
    @Test
    @DisplayName("provide a formatted today's date")
    public void provideFormattedTodayDate() {
        Clock clock = new ClockStub();

        String date = clock.dateAsString();

        assertThat(date).isEqualTo("04/12/2021");
    }
}