package se.iths.christoffer.labb2christoffer.service;

import org.springframework.stereotype.Service;
import se.iths.christoffer.labb2christoffer.component.AccountComponent;
import se.iths.christoffer.labb2christoffer.exceptions.InsufficientFundsException;
import se.iths.christoffer.labb2christoffer.exceptions.InvalidAmountException;
import se.iths.christoffer.labb2christoffer.exceptions.MaxWithdrawalExceededException;

@Service
public class ATMService {
    private final AccountComponent accountComponent;
    private int maximumWithdrawSum = 500;

    public ATMService(AccountComponent accountComponent) {
        this.accountComponent = accountComponent;
    }

    public void depositMoney(int sum) {
        accountComponent.getBalance();
        if (sum <= 0) {
            throw new InvalidAmountException("Instättningsbeloppet är ogiltigt!");
        }
        accountComponent.depositMoney(sum);
    }

    public void withdrawMoney(int sum) {
        int balance = accountComponent.getBalance();

        if (sum <= 0) {
            throw new InvalidAmountException("Insättningsbeloppet är ogiltigt!");
        }
        if (sum > maximumWithdrawSum) {
            throw new MaxWithdrawalExceededException("Beloppet överstiger maxgränsen för uttag.");
        }
        if (sum > balance) {
            throw new InsufficientFundsException("Du saknar täckning för detta belopp.");
        }
        accountComponent.withdrawMoney(sum);
    }

    public int getBalance() {
        return accountComponent.getBalance();
    }
}
