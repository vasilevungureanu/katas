package com.vasileungureanu.katas.bankwithoutmockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrintStatementFeature {
    private ConsoleMock console;
    private Account account;

    @BeforeEach
    public void initialize() {
        Clock clock = new ClockStub();
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        console = new ConsoleMock();
        StatementPrinter statementPrinter = new StatementPrinter(console);
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    @DisplayName("print a statement that contains all transactions")
    public void printStatementContainingAllTransactions() {
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);

        account.printStatement();

        String statement = "DATE | AMOUNT | BALANCE\n"
                + "04/12/2021 | 500.00 | 1400.00\n"
                + "04/12/2021 | -100.00 | 900.00\n"
                + "04/12/2021 | 1000.00 | 1000.00";

        console.expect(statement);
        console.verify();
    }
}