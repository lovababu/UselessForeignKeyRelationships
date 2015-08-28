package org.avol.hibernate.repository;

import org.avol.hibernate.entities.Account;

import java.io.Serializable;

/**
 * Created by Durga on 8/28/2015.
 */
public interface AccountRepository {

    Serializable save(Account account);

    Account get(int accountId);

    void delete(int accountId);

    Account update(Account account);
}
