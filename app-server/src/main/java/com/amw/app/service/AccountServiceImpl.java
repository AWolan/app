package com.amw.app.service;

import com.amw.app.builder.AccountBuilder;
import com.amw.app.exception.AccountNumberException;
import com.amw.app.exception.BuilderException;
import com.amw.app.model.Account;
import com.amw.app.model.Person;
import com.amw.app.reporitory.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Person owner, String accountNumber) {
        Account account = null;
        try {
            account = account()
                    .owner(owner)
                    .number()
                        .accountNumber(accountNumber)
                        .backToAccount()
                    .build();
            account = accountRepository.save(account);
        } catch (BuilderException | AccountNumberException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public List<Account> getAccountList(Long ownerId) {
        return accountRepository.getListByOwnerId(ownerId);
    }

    private AccountBuilder account() {
        return new AccountBuilder();
    }

}
