package org.avol.hibernate.service.impl;

import org.avol.hibernate.entities.Account;
import org.avol.hibernate.repository.AccountRepository;
import org.avol.hibernate.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by Durga on 8/28/2015.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Serializable save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account get(int accountId) {
        Account dbAccount = accountRepository.get(accountId);
        if (dbAccount != null) {
            dbAccount.getUsers().stream().forEach(user -> user.getUserName());
        }
        return dbAccount;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int accountId) {
        accountRepository.delete(accountId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Account update(Account account) {
        return accountRepository.update(account);
    }
}
