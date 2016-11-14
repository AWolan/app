package com.amw.app.model;

import com.amw.app.exception.AccountNumberException;

import javax.persistence.Entity;

/**
 * Gathers information about account number.
 */
@Entity
public class AccountNumber {

    private static final String DEFAULT_IBAN = "PL";

    private static final Integer LENGTH_WITH_IBAN = 28;
    private static final Integer LENGTH_WITHOUT_IBAN = 26;
    private static final Integer MOD = 97;
    private static final Integer REST = 1;
    private static final Integer RADIX = 10;
    private static final Integer CS_LENGTH = 2;
    private static final Integer BN_LENGTH = 8;
    private static final Integer AN_LENGTH = 16;

    private String iban;
    private Long controlSum;
    private Long bankNumber;
    private Long accountNumber;

    public AccountNumber() {
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getControlSum() {
        return getValue(controlSum, CS_LENGTH);
    }

    public void setControlSum(String controlSum) throws AccountNumberException {
        try {
            this.controlSum = Long.parseLong(controlSum, RADIX);
        } catch (NumberFormatException e) {
            throw new AccountNumberException("Control sum is not valid.");
        }
    }

    public String getBankNumber() {
        return getValue(bankNumber, BN_LENGTH);
    }

    public void setBankNumber(String bankNumber) throws AccountNumberException {
        try {
            this.bankNumber = Long.parseLong(bankNumber, RADIX);
        } catch (NumberFormatException e) {
            throw new AccountNumberException("Bank number is not valid.");
        }
    }

    public String getAccountNumber() {
        return getValue(accountNumber, AN_LENGTH);
    }

    public void setAccountNumber(String accountNumber) throws AccountNumberException {
        try {
            this.accountNumber = Long.parseLong(accountNumber, RADIX);
        } catch (NumberFormatException e) {
            throw new AccountNumberException("Account number is not valid.");
        }
    }

    public String getFullNumber(boolean withIban) {
        String controlSum = getControlSum();
        String bankNumber = getBankNumber();
        String accountNumber = getAccountNumber();

        if (withIban) {
            return String.format("%s%s%s%s", iban, controlSum, bankNumber, accountNumber);
        } else {
            return String.format("%s%s%s", controlSum, bankNumber, accountNumber);
        }
    }

    public void setFullNumber(String fullNumber) throws AccountNumberException {
        if (fullNumber != null) {
            if (fullNumber.length() == LENGTH_WITH_IBAN) {
                String iban = fullNumber.substring(0, 2);
                String fullNumberWithoutIban = fullNumber.substring(2);
                setFullNumber(iban, fullNumberWithoutIban);
            } else if (fullNumber.length() == LENGTH_WITHOUT_IBAN) {
                setFullNumber(DEFAULT_IBAN, fullNumber);
            } else {
                throw new AccountNumberException("Account number's length is not valid.");
            }
        } else {
            throw new AccountNumberException("Account number is not present.");
        }
    }

    private void setFullNumber(String iban, String fullNumber) throws AccountNumberException {
        String numberValueForIban = getNumberValueForIban(iban);
        String controlSum = fullNumber.substring(0, 2);
        String bankNumber = fullNumber.substring(2, 10);
        String accountNumber = fullNumber.substring(10);

        if (isControlSumValid(numberValueForIban, controlSum, bankNumber, accountNumber)) {
            setIban(iban);
            setControlSum(controlSum);
            setBankNumber(bankNumber);
            setAccountNumber(accountNumber);
        } else {
            throw new AccountNumberException("Account number is not valid");
        }
    }

    private boolean isControlSumValid(String ibanNumber, String controlSum, String bankNumber, String accountNumber) {
        String format = String.format("%s%s%s%s", bankNumber, accountNumber, ibanNumber, controlSum);
        long number = Long.parseLong(format, RADIX);
        return number % MOD == REST;
    }

    private String getNumberValueForIban(String eban) {
        int numeric1 = Character.getNumericValue(eban.charAt(0));
        int numeric2 = Character.getNumericValue(eban.charAt(1));
        return String.format("%02d%02d", numeric1, numeric2);
    }

    private String getValue(Long value, int length) {
        String format = String.format("%%0%d", length);
        return String.format(format, value);
    }
}
