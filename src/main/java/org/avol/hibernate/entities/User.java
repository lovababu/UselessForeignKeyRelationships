package org.avol.hibernate.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Durga on 8/28/2015.
 */

@Entity
@Table(name = "ACCOUNT_USER")
@Setter @Getter
public class User {

    @Id
    @Column(name = "USER_ID")
    @GenericGenerator(name = "increment",  strategy = "increment")
    @GeneratedValue(generator = "increment")
    private int id;

    @Column(name = "USER_NAME")
    private String userName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Account.class)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;
}
