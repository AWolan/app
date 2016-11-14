package com.amw.app.service;

import com.amw.app.model.Account;
import com.amw.app.model.Person;

import java.util.List;

/**
 * Manages account actions.
 */
public interface AccountService {

    Account createAccount(Person owner, String accountNumber);

    List<Account> getAccountList(Long ownerId);
}
