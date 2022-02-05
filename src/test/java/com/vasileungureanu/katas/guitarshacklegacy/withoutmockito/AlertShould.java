package com.vasileungureanu.katas.guitarshacklegacy.withoutmockito;

import com.vasileungureanu.katas.guitarshacklegacy.Alert;
import com.vasileungureanu.katas.guitarshacklegacy.Console;
import com.vasileungureanu.katas.guitarshacklegacy.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Alert should")
public class AlertShould {
    private Alert alert;
    private Product product;

    private boolean printLineCalled;

    @BeforeEach
    public void setUp() {
        printLineCalled = false;

        Console console = new Console() {
            @Override
            public void printLine(String text) {
                printLineCalled = true;
            }
        };

        alert = new Alert(console);
        product = new Product(811, 10, 14);
    }

    @Test
    @DisplayName("Print text")
    public void printText() {
        alert.send(product);

        assertThat(printLineCalled).isTrue();
    }
}
