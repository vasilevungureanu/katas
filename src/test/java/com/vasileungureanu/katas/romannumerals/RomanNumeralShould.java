package com.vasileungureanu.katas.romannumerals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Roman Numeral should")
public final class RomanNumeralShould {
    private RomanNumeral romanNumeral;

    @BeforeEach
    public void setUp() {
        romanNumeral = new RomanNumeral();
    }

    @ParameterizedTest(name = "Convert {0} into {1}")
    @CsvSource({
            "1, I",
            "2, II",
            "3, III",
            "4, IV",
            "5, V",
            "7, VII",
            "9, IX",
            "10, X",
            "17, XVII",
            "30, XXX",
            "38, XXXVIII",
            "479, CDLXXIX",
            "3999, MMMCMXCIX"
    })
    public void convertArabicNumbersIntoTheirRespectiveRomanNumeral(int arabic, String roman) {
        assertThat(romanNumeral.convert(arabic)).isEqualTo(roman);
    }
}