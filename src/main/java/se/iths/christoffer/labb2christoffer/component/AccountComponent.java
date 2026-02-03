package se.iths.christoffer.labb2christoffer.component;

import org.springframework.stereotype.Component;

@Component
public class AccountComponent {
    private int balance = 0;

    public void withdrawMoney(int sum) {
        balance = balance - sum;
    }

    public void depositMoney(int sum) {
        balance = balance + sum;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}