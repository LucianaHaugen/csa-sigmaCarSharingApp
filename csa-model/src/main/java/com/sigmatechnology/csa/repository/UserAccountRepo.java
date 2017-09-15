package com.sigmatechnology.csa.repository;

import com.sigmatechnology.csa.entity.account.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lucianahaugen on 04/09/17.
 */
@Repository
public class UserAccountRepo extends CrudRepo<UserAccount> implements UserAccountDao{


    @Override
    public List<UserAccount> findAllNotDeleted() {
        return entityManager.createNamedQuery(UserAccount.FIND_ALL_NON_DELEETED, UserAccount.class)
            .getResultList();
    }

    @Override
    public UserAccount findByUsername(String userName) {
        return entityManager.createNamedQuery(UserAccount.FIND_BY_USERNAME, UserAccount.class)
            .setParameter("userName", userName)
            .getSingleResult();
    }
}
