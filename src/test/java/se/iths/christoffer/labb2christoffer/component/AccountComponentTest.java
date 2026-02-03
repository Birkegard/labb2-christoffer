package se.iths.christoffer.labb2christoffer.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountComponentTest {
    private AccountComponent accountComponent;

    @BeforeEach
    public void setUp() {
        accountComponent = new AccountComponent();

    }

    @Test
    public void testWithdrawMoney() {
        accountComponent.withdrawMoney(300);
        assertEquals(-300, accountComponent.getBalance());
    }

    @Test
    public void testDepositMoney() {
        accountComponent.depositMoney(300);
        assertEquals(300, accountComponent.getBalance());
    }

    @Test
    public void testDepositAndWithdrawMoney() {
        accountComponent.depositMoney(500);
        accountComponent.withdrawMoney(250);

        assertEquals(250, accountComponent.getBalance());
    }

    @Test
    public void testGetBalance() {
        assertEquals(0, accountComponent.getBalance());
    }
}