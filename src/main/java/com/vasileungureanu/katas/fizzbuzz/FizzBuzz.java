package com.vasileungureanu.katas.fizzbuzz;

public final class FizzBuzz {
    public String convert(int number) {
        if (divisibleBy(15, number))
            return "FizzBuzz";
        else if (divisibleBy(3, number))
            return "Fizz";
        else if (divisibleBy(5, number))
            return "Buzz";
        else
            return String.valueOf(number);
    }

    private boolean divisibleBy(int factor, int number) {
        return number % factor == 0;
    }
}
