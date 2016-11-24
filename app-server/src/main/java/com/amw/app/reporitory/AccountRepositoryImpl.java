package com.amw.app.reporitory;

import com.amw.app.model.Account;
import com.amw.app.model.QAccount;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRepositoryImpl extends BaseRepositoryImpl<Account, Long> implements AccountRepository {

    private QAccount account = QAccount.account;

//    @Autowired
//    private BaseDaoCustom accountDao;
//    @Autowired
//    public AccountRepositoryImpl(BaseDaoCustom accountDao) {
//        dao = accountDao;
//    }

//    @Autowired
//    private AccountDao accountDao;

    @Override
    public List<Account> getListByOwnerId(Long ownerId) {
        BooleanExpression isGivenOwnerId = account.owner.id.eq(ownerId);
//        return accountDao.query()
//                .where(isGivenOwnerId)
//                .select(account)
//                .fetch();
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
