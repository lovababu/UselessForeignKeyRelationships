package org.avol.hibernate.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Durga on 8/28/2015.
 */

@Entity
@Table(name = "ACCOUNT")
@Setter @Getter
public class Account {

    @Id
    @Column(name = "ACCOUNT_ID")
    @GenericGenerator(name = "increment",  strategy = "increment")
    @GeneratedValue(generator = "increment")
    private int id;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users;

    public void addUser(User user){
        if (users == null) {
            users = new HashSet<User>();
        }
        users.add(user);
    }
}
