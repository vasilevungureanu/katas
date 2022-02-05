package com.vasileungureanu.katas.nthfibonacci;

public final class Fibonacci {
    public int number(int position) {
        if (position < 2)
            return position;

        return number(position - 1) + number(position - 2);
    }
}