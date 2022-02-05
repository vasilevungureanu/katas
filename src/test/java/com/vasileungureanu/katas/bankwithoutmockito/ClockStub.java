package com.vasileungureanu.katas.bankwithoutmockito;

import java.time.LocalDate;

public class ClockStub extends Clock {
    @Override
    protected LocalDate today() {
        return LocalDate.of(2021, 12, 4);
    }
}