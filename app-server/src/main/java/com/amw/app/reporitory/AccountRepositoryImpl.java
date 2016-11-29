package com.amw.app.reporitory;

import com.amw.app.dao.AccountDao;
import com.amw.app.model.Account;
import com.amw.app.model.QAccount;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRepositoryImpl implements AccountRepository {

    private QAccount account = QAccount.account;

    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> getListByOwnerId(Long ownerId) {
        BooleanExpression isGivenOwnerId = account.owner.id.eq(ownerId);
        return accountDao.query()
                .where(isGivenOwnerId)
                .select(account)
                .fetch();
    }

    @Override
    public Account getOneById(Long id) {
        return accountDao.findOne(id);
    }

    @Override
    public Account save(Account account) {
        return accountDao.save(account);
    }

}
