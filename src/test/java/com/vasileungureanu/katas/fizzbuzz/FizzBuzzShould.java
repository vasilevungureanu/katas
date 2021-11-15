package com.vasileungureanu.katas.fizzbuzz;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public final class FizzBuzzShould {
    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "4,4"})
    void convertNumberToString(int number, String output) {
        assertThat(convert(number)).isEqualTo(output);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9})
    void convertNumberToFizz(int number) {
        assertThat(convert(number)).isEqualTo("Fizz");
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20})
    void convertNumberToBuzz(int number) {
        assertThat(convert(number)).isEqualTo("Buzz");
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45})
    void convertNumberToFizzBuzz(int number) {
        assertThat(convert(number)).isEqualTo("FizzBuzz");
    }

    private String convert(int number) {
        return new FizzBuzz().convert(number);
    }
}