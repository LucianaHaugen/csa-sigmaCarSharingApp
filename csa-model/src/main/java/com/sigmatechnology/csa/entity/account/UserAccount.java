package com.sigmatechnology.csa.entity.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sigmatechnology.csa.entity.AbstractBaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lucianahaugen on 04/09/17.
 */

@NamedQueries({
    @NamedQuery(
        name = UserAccount.FIND_ALL_NON_DELEETED,
        query = "SELECT u FROM UserAccount u " +
            "WHERE u.deleted <> true"
    ),
    @NamedQuery(
        name = UserAccount.FIND_BY_USERNAME,
        query = "SELECT u FROM UserAccount u " +
            "WHERE u.username = :username AND u.deleted <> true"
    )
})


@Entity
public class UserAccount extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL_NON_DELEETED = "FindAllNonDeleted";
    public static final String FIND_BY_USERNAME = "FindByUsername";

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private int userId;

    @Column(nullable = false)
    private Boolean deleted = false;

    @JsonIgnore
    @Column(nullable = false)
    private String password;


    private List<UserRole> roles;

    public UserAccount() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}
