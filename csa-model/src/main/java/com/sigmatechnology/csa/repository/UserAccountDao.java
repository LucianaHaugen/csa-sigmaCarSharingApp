package com.sigmatechnology.csa.repository;

import com.sigmatechnology.csa.entity.account.UserAccount;

import java.util.List;

/**
 * Created by lucianahaugen on 04/09/17.
 */
public interface UserAccountDao extends CrudDao<UserAccount> {

    List<UserAccount> findAllNotDeleted();
    UserAccount findByUsername(String userName);
}
