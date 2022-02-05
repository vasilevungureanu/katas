package com.vasileungureanu.katas.nthfibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public final class FibonacciShould {
    private Fibonacci fibonacci;

    @BeforeEach
    public void setUp() {
        fibonacci = new Fibonacci();
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 1",
            "3, 2",
            "4, 3",
            "5, 5",
            "6, 8",
            "7, 13",
            "8, 21",
            "9, 34"
    })
    public void generateNumberForPosition(int position, int number) {
        assertThat(fibonacci.number(position)).isEqualTo(number);
    }
}