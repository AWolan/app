package com.amw.app.builder;

import com.amw.app.exception.BuilderException;
import com.amw.app.model.Account;
import com.amw.app.model.AccountNumber;
import com.amw.app.model.Person;

/**
 * Builder for account.
 */
public class AccountBuilder extends BaseBuilder<Account> {

    private PersonBuilder personBuilder;
    private AccountNumberBuilder accountNumberBuilder;

    public AccountBuilder() {
        super(new Account());
    }

    public AccountBuilder id(Long id) {
        get().setId(id);
        return this;
    }

    public PersonBuilder owner() {
        personBuilder = new PersonBuilder(this);
        return personBuilder;
    }

    public AccountBuilder owner(Person owner) {
        get().setOwner(owner);
        return this;
    }

    public AccountNumberBuilder number() {
        accountNumberBuilder = new AccountNumberBuilder(this);
        return accountNumberBuilder;
    }

    public AccountBuilder number(AccountNumber accountNumber) {
        get().setNumber(accountNumber);
        return this;
    }

    public Account build() throws BuilderException {
        Account account = get();

        if (personBuilder != null) {
            Person owner = personBuilder.build();
            account.setOwner(owner);
        }
        if (accountNumberBuilder != null) {
            AccountNumber accountNumber = accountNumberBuilder.build();
            account.setNumber(accountNumber);
        }

        if (account.getOwner() == null) {
            throw new BuilderException("Account: owner is not present");
        }
        if (account.getOwner() == null) {
            throw new BuilderException("Account: account number is not present");
        }

        return account;
    }

}
