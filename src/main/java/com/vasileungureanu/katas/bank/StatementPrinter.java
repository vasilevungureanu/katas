package com.vasileungureanu.katas.bank;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {
    private static final String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";

    private final Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        console.printLine(STATEMENT_HEADER);

        printStatementLines(transactions);
    }

    private void printStatementLines(List<Transaction> transactions) {
        AtomicInteger runningBalance = new AtomicInteger(0);
        transactions
                .stream()
                .map(transaction -> statementLine(runningBalance, transaction))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(console::printLine);
    }

    private String statementLine(AtomicInteger runningBalance, Transaction transaction) {
        return transaction.date() +
                " | " +
                formatDecimal(transaction.amount()) +
                " | " +
                formatDecimal(runningBalance.addAndGet(transaction.amount()));
    }

    private String formatDecimal(int amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(amount);
    }
}