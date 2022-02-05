package com.vasileungureanu.katas.guitarshacklegacy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("Alert should")
public class AlertShould {
    private Console console;
    private Alert alert;
    private Product product;

    @BeforeEach
    public void setUp() {
        console = mock(Console.class);
        alert = new Alert(console);
        product = new Product(811, 10, 14);
    }

    @Test
    @DisplayName("Print text")
    public void printText() {
        alert.send(product);

        verify(console).printLine(any());
    }
}
