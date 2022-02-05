package com.vasileungureanu.katas.bankwithoutmockito;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleMock extends Console {
    private String text;
    private String expectedText;

    @Override
    public void print(String text) {
        super.print(text);

        this.text = text;
    }

    public void expect(String text) {
        this.expectedText = text;
    }

    public void verify() {
        assertThat(text).isEqualTo(expectedText);
    }
}