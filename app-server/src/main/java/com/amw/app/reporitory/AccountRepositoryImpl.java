package com.amw.app.reporitory;

import com.amw.app.model.Account;
import com.amw.app.model.QAccount;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositoryImpl extends BaseRepositoryImpl<Account, Long> implements AccountRepository {

    private QAccount account = QAccount.account;

    @Override
    public List<Account> getListByOwnerId(Long ownerId) {
        BooleanExpression isGivenOwnerId = account.owner.id.eq(ownerId);
        return getList(isGivenOwnerId);
    }

    @Override
    public Account getOneById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Account save(Account account) {
        return dao.save(account);
    }

}
