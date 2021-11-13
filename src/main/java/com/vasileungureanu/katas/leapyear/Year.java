package com.vasileungureanu.katas.leapyear;

public final class Year {
    private final int year;

    public Year(int year) {
        this.year = year;
    }

    public boolean isLeapYear() {
        return divisibleBy(year, 4) && !(divisibleBy(year, 100) && !divisibleBy(year, 400));
    }

    private boolean divisibleBy(int year, int divisor) {
        return year % divisor == 0;
    }
}