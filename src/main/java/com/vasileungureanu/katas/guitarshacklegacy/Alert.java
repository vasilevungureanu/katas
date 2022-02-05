package com.vasileungureanu.katas.guitarshacklegacy;

public class Alert {
    private final Console console;

    public Alert(Console console) {
        this.console = console;
    }

    public void send(Product product) {
        String text = "You need to reorder product " + product.getId() +
                ". Only " + product.getStock() + " remaining in stock";

        console.printLine(text);
    }
}