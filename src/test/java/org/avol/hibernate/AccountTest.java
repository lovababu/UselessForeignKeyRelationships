package org.avol.hibernate;

import org.avol.hibernate.config.AppConfig;
import org.avol.hibernate.entities.Account;
import org.avol.hibernate.entities.User;
import org.avol.hibernate.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Durga on 8/28/2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AccountTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void testCrudOperations() {
        //Creating Account.
        Serializable id = accountService.save(getAccount("Avol's"));
        assertNotNull(id);
        //Retriving Account.
        Account account = accountService.get((Integer)id);
        assertNotNull(account);
        assertEquals(account.getDisplayName(), "Avol's");
        assertNotNull(account.getUsers());
        assertTrue(account.getUsers().size() > 0);

        //Updating the Account.
        account.setDisplayName("Lova's");
        Account updatedAcct = accountService.update(account);
        assertNotNull(updatedAcct);
        assertEquals(updatedAcct.getDisplayName(), "Lova's");
        assertNotNull(updatedAcct.getUsers());
        assertTrue(updatedAcct.getUsers().size() > 0);

        //Delete the Account;
        accountService.delete(updatedAcct.getId());
        Account afterDelete = accountService.get(updatedAcct.getId());
        assertNull(afterDelete); //this should be null.
    }

    private Account getAccount(String displayName) {
        Account account = new Account();
        account.setDisplayName(displayName);

        User user = new User();
        user.setAccount(account);
        user.setUserName(displayName + new Random().nextInt(100));

        account.addUser(user);
        return account;
    }
}
