package se.iths.christoffer.labb2christoffer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.christoffer.labb2christoffer.component.AccountComponent;
import se.iths.christoffer.labb2christoffer.exceptions.InsufficientFundsException;
import se.iths.christoffer.labb2christoffer.exceptions.InvalidAmountException;
import se.iths.christoffer.labb2christoffer.exceptions.MaxWithdrawalExceededException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ATMServiceTest {
    @InjectMocks
    ATMService atmService;

    @Mock
    AccountComponent accountComponent;


    @Test
    public void testDepositMoney() {
        atmService.depositMoney(500);
        verify(accountComponent).depositMoney(500);
    }

    @Test
    public void testWithdrawMoney() {
        when(accountComponent.getBalance()).thenReturn(1000);
        atmService.withdrawMoney(300);

        verify(accountComponent).withdrawMoney(300);
    }

    @Test
    public void testGetBalance() {
        when(accountComponent.getBalance()).thenReturn(1000);

        assertEquals(1000, atmService.getBalance());
    }

    @Test
    public void testInvalidAmountExceptionThroughDeposit() {
        int deposit = 0;
        assertThrows(InvalidAmountException.class, () -> {
            atmService.depositMoney(deposit);
        });
    }

    @Test
    public void testInvalidAmountExceptionThroughWithdraw() {
        int withdraw = 0;
        assertThrows(InvalidAmountException.class, () -> {
            atmService.withdrawMoney(withdraw);
        });
    }

    @Test
    public void testMaxWithdrawalExceededException() {
        int withdraw = 1000;

        assertThrows(MaxWithdrawalExceededException.class, () -> {
            atmService.withdrawMoney(withdraw);
        });
    }

    @Test
    public void testInsufficientFundsException() {
        int balance = 0;

        assertThrows(InsufficientFundsException.class, () -> {
            atmService.withdrawMoney(500);
        });
    }
}