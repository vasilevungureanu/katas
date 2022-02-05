package com.vasileungureanu.katas.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Statement Printer should")
public class StatementPrinterShould {
    private static final List<Transaction> NO_TRANSACTIONS = Collections.emptyList();

    @Mock
    Console console;

    private StatementPrinter statementPrinter;

    @BeforeEach
    public void initialize() {
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    @DisplayName("always print a header")
    public void alwaysPrintHeader() {
        statementPrinter.print(NO_TRANSACTIONS);

        verify(console).printLine("DATE | AMOUNT | BALANCE");
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

        statementPrinter.print(transactions);

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1200.00");
        inOrder.verify(console).printLine("05/04/2014 | -200.00 | 700.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
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