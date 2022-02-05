package com.vasileungureanu.katas.statscalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Stats Calculator should")
public final class StatsCalculatorShould {
    private StatsCalculator statsCalculator;

    @BeforeEach
    public void setUp() {
        List<Integer> sequence = Arrays.asList(6, 9, 15, -2, 92, 11);
        statsCalculator = new StatsCalculator(sequence);
    }

    @Test
    @DisplayName("determine minimum value")
    public void determineMinimumValue() {
        assertThat(statsCalculator.minimumValue()).isEqualTo(-2);
    }

    @Test
    @DisplayName("determine maximum value")
    public void determineMaximumValue() {
        assertThat(statsCalculator.maximumValue()).isEqualTo(92);
    }

    @Test
    @DisplayName("determine number of elements")
    public void determineNumberOfElements() {
        assertThat(statsCalculator.numberOfElements()).isEqualTo(6);
    }

    @Test
    @DisplayName("determine average value")
    public void determineAverageValue() {
        assertThat(statsCalculator.averageValue()).isEqualTo(22);
    }
}