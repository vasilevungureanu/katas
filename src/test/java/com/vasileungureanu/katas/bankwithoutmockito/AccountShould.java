package com.vasileungureanu.katas.bankwithoutmockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Account should")
public class AccountShould {
    public static final String TODAY = "04/12/2021";

    private TransactionRepository transactionRepository;
    private Account account;

    @BeforeEach
    public void initialize() {
        Clock clock = new ClockStub();
        transactionRepository = new TransactionRepository(clock);
        Console console = new Console();
        StatementPrinter statementPrinter = new StatementPrinter(console);
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    @DisplayName("store a deposit transaction")
    public void storeDepositTransaction() {
        account.deposit(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions.size()).isEqualTo(1);
        assertThat(transactionRepository.allTransactions().get(0)).isEqualTo(transaction(100));
    }

    @Test
    @DisplayName("store a withdrawal transaction")
    public void storeWithdrawalTransaction() {
        account.withdraw(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions.size()).isEqualTo(1);
        assertThat(transactionRepository.allTransactions().get(0)).isEqualTo(transaction(-100));
    }

    @Test
    @DisplayName("print a statement")
    public void printStatement() {
        Clock clock = new ClockStub();
        TransactionRepository transactionRepository = new TransactionRepositoryStub(clock);
        ConsoleMock console = new ConsoleMock();
        StatementPrinter statementPrinter = new StatementPrinter(console);
        Account account = new Account(transactionRepository, statementPrinter);

        account.printStatement();

        String statement = "DATE | AMOUNT | BALANCE\n"
                + "04/12/2021 | 100.00 | 100.00";

        console.expect(statement);
        console.verify();
    }

    private Transaction transaction(int amount) {
        return new Transaction(TODAY, amount);
    }

    private static class TransactionRepositoryStub extends TransactionRepository {
        public TransactionRepositoryStub(Clock clock) {
            super(clock);
        }

        @Override
        public List<Transaction> allTransactions() {
            return List.of(new Transaction(TODAY, 100));
        }
    }
}