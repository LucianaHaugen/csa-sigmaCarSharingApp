package com.sigmatechnology.csa.repository;

import com.sigmatechnology.csa.entity.account.UserRole;
import com.sigmatechnology.csa.entity.account.UserRoles;

/**
 * Created by lucianahaugen on 04/09/17.
 */
public interface UserRoleDao extends CrudDao<UserRole>{

    UserRole findByRole(UserRoles role);
}
