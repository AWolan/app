package com.amw.app.reporitory;

import com.amw.app.model.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> getListByOwnerId(Long ownerId);

    Account getOneById(Long id);

    Account save(Account account);
}
