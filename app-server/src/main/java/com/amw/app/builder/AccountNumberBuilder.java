package com.amw.app.builder;

import com.amw.app.exception.AccountNumberException;
import com.amw.app.exception.BuilderException;
import com.amw.app.model.AccountNumber;

/**
 * Builder for account number.
 */
public class AccountNumberBuilder extends BaseBuilder<AccountNumber> {

    private AccountBuilder accountBuilder;

    public AccountNumberBuilder(AccountBuilder accountBuilder) {
        super(new AccountNumber());
        this.accountBuilder = accountBuilder;
    }

    public AccountNumberBuilder fullNumber(String fullNumber) throws AccountNumberException {
        get().setFullNumber(fullNumber);
        return this;
    }

    public AccountNumberBuilder iban(String iban) {
        get().setIban(iban);
        return this;
    }

    public AccountNumberBuilder controlSum(String controlSum) throws AccountNumberException {
        get().setControlSum(controlSum);
        return this;
    }

    public AccountNumberBuilder bankNumber(String bankNumber) throws AccountNumberException {
        get().setBankNumber(bankNumber);
        return this;
    }

    public AccountNumberBuilder accountNumber(String accountNumber) throws AccountNumberException {
        get().setAccountNumber(accountNumber);
        return this;
    }

    public AccountBuilder backToAccount() throws BuilderException {
        if (accountBuilder != null) {
            return accountBuilder;
        } else {
            throw new BuilderException("Cannot return to account builder");
        }
    }

    public AccountNumber build() throws BuilderException {
        AccountNumber accountNumber = get();

        if (accountNumber.getIban() == null) {
            throw new BuilderException("Account Number: IBAN is not present");
        }
        if (accountNumber.getControlSum() == null) {
            throw new BuilderException("Account Number: control sum is not present");
        }
        if (accountNumber.getBankNumber() == null) {
            throw new BuilderException("Account Number: bank number is not present");
        }
        if (accountNumber.getAccountNumber() == null) {
            throw new BuilderException("Account Number: account number is not present");
        }

        return accountNumber;
    }
}
