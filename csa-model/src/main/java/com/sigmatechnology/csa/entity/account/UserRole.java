package com.sigmatechnology.csa.entity.account;

import com.sigmatechnology.csa.entity.AbstractBaseEntity;

import javax.persistence.*;

/**
 * Created by lucianahaugen on 04/09/17.
 */

@NamedQueries({
    @NamedQuery(
        name = UserRole.FIND_BY_ROLE,
        query = "SELECT r FROM UserRole r " +
            "WHERE r.role = :role"
    )
})
@Entity
public class UserRole extends AbstractBaseEntity{

    public static final long serialVersionUID = 1L;

    public static final String FIND_BY_ROLE = "FindByRole";


    @Enumerated(value = EnumType.STRING)
    private UserRoles role;


    public UserRole() {

    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
}
