package com.vasileungureanu.katas.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Account should")
public class AccountShould {
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    private StatementPrinter statementPrinter;

    private Account account;

    @BeforeEach
    public void initialize() {
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    @DisplayName("store a deposit transaction")
    public void storeDepositTransaction() {
        account.deposit(100);

        verify(transactionRepository).addDeposit(100);
    }

    @Test
    @DisplayName("store a withdrawal transaction")
    public void storeWithdrawalTransaction() {
        account.withdraw(100);

        verify(transactionRepository).addWithdraw(100);
    }

    @Test
    @DisplayName("print a statement")
    public void printStatement() {
        List<Transaction> transactions = List.of(new Transaction("12/05/2015", 100));
        given(transactionRepository.allTransactions()).willReturn(transactions);

        account.printStatement();

        verify(statementPrinter).print(transactions);
    }
}