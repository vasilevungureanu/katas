package com.vasileungureanu.katas.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Transaction Repository should")
public class TransactionRepositoryShould {
    public static final String TODAY = "12/05/2015";

    @Mock
    Clock clock;

    private TransactionRepository transactionRepository;

    @BeforeEach
    public void initialize() {
        transactionRepository = new TransactionRepository(clock);

        given(clock.dateAsString()).willReturn(TODAY);
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