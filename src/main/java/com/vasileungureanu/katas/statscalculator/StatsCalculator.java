package com.vasileungureanu.katas.statscalculator;

import java.util.Collections;
import java.util.List;

public final class StatsCalculator {
    private final List<Integer> sequence;

    public StatsCalculator(List<Integer> sequence) {
        this.sequence = sequence;
    }

    public int minimumValue() {
        return Collections.min(sequence);
    }

    public int maximumValue() {
        return Collections.max(sequence);
    }

    public int numberOfElements() {
        return sequence.size();
    }

    public long averageValue() {
        return Math.round(
                sequence
                        .stream()
                        .mapToInt(i -> i)
                        .average()
                        .orElse(0.0)
        );
    }
}