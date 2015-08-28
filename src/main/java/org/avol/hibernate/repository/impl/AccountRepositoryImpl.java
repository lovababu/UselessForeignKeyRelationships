package org.avol.hibernate.repository.impl;

import org.avol.hibernate.entities.Account;
import org.avol.hibernate.repository.AccountRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Durga on 8/28/2015.
 */

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public AccountRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Serializable save(Account account) {
        return sessionFactory.getCurrentSession().save(account);
    }

    @Override
    public Account get(int accountId) {
        return (Account) sessionFactory.getCurrentSession().get(Account.class, accountId);
    }

    @Override
    public void delete(int accountId) {
        Account account = new Account();
        account.setId(accountId);
        sessionFactory.getCurrentSession().delete(account);
    }

    @Override
    public Account update(Account account) {
        Account dbAccount = get(account.getId());
        dbAccount.setDisplayName(account.getDisplayName());
        dbAccount.setUsers(account.getUsers());
        sessionFactory.getCurrentSession().saveOrUpdate(dbAccount);
        return dbAccount;
    }
}
