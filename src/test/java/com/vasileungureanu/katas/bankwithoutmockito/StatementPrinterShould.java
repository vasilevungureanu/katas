package com.vasileungureanu.katas.bankwithoutmockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@DisplayName("Statement Printer should")
public class StatementPrinterShould {
    private static final List<Transaction> NO_TRANSACTIONS = Collections.emptyList();

    ConsoleMock console;
    private StatementPrinter statementPrinter;

    @BeforeEach
    public void initialize() {
        console = new ConsoleMock();
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    @DisplayName("always print a header")
    public void alwaysPrintHeader() {
        statementPrinter.print(NO_TRANSACTIONS);

        console.expect("DATE | AMOUNT | BALANCE");
        console.verify();
    }

    @Test
    @DisplayName("print transactions in the reverse chronological order")
    public void printTransactionsInReverseChronologicalOrder() {
        List<Transaction> transactions = transactionsContaining(
                deposit("01/04/2014", 1000),
                withdrawal("02/04/2014", 100),
                withdrawal("05/04/2014", 200),
                deposit("10/04/2014", 500)
        );

        String statement = "DATE | AMOUNT | BALANCE\n"
                + "10/04/2014 | 500.00 | 1200.00\n"
                + "05/04/2014 | -200.00 | 700.00\n"
                + "02/04/2014 | -100.00 | 900.00\n"
                + "01/04/2014 | 1000.00 | 1000.00";

        statementPrinter.print(transactions);

        console.expect(statement);
        console.verify();
    }

    private List<Transaction> transactionsContaining(Transaction... transactions) {
        return Arrays.asList(transactions);
    }

    private Transaction withdrawal(String date, int amount) {
        return new Transaction(date, -amount);
    }

    private Transaction deposit(String date, int amount) {
        return new Transaction(date, amount);
    }
}