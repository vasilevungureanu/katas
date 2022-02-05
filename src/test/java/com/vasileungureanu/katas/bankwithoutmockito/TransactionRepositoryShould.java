package com.vasileungureanu.katas.bankwithoutmockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Transaction Repository should")
public class TransactionRepositoryShould {
    public static final String TODAY = "04/12/2021";

    private TransactionRepository transactionRepository;

    @BeforeEach
    public void initialize() {
        Clock clock = new ClockStub();
        transactionRepository = new TransactionRepository(clock);
    }

    @Test
    @DisplayName("create and store a deposit transaction")
    public void createAndStoreDepositTransaction() {
        transactionRepository.addDeposit(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions.size()).isEqualTo(1);
        assertThat(transactions.get(0)).isEqualTo(transaction(100));
    }

    @Test
    @DisplayName("create and store a withdrawal transaction")
    public void createAndStoreWithdrawalTransaction() {
        transactionRepository.addWithdraw(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions.size()).isEqualTo(1);
        assertThat(transactions.get(0)).isEqualTo(transaction(-100));
    }

    private Transaction transaction(int amount) {
        return new Transaction(TODAY, amount);
    }
}