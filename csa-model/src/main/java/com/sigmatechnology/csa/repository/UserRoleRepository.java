package com.sigmatechnology.csa.repository;

import com.sigmatechnology.csa.entity.account.UserRole;
import com.sigmatechnology.csa.entity.account.UserRoles;
import org.springframework.stereotype.Repository;

/**
 * Created by lucianahaugen on 04/09/17.
 */
@Repository
public class UserRoleRepository extends CrudRepository<UserRole> implements UserRoleDao{

    @Override
    public UserRole findByRole(UserRoles role) {
        return entityManager.createNamedQuery(UserRole.FIND_BY_ROLE, UserRole.class)
            .setParameter("role", role)
            .getSingleResult();
    }
}
