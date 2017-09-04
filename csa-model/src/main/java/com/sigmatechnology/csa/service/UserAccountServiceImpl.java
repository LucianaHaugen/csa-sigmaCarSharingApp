package com.sigmatechnology.csa.service;

import com.sigmatechnology.csa.entity.account.UserAccount;
import com.sigmatechnology.csa.entity.account.UserRole;
import com.sigmatechnology.csa.repository.UserAccountDao;
import com.sigmatechnology.csa.repository.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by lucianahaugen on 04/09/17.
 */
@Service
public class UserAccountServiceImpl implements UserAccountService{

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public UserAccount createAccount(UserAccount userAccount) {
        if(Objects.nonNull(userAccount.getRoles())){
            userAccount
                .getRoles()
                .forEach((role) -> {
                    UserRole userRoleDb = userRoleDao.findByRole(role.getRole());
                    role.setId(userRoleDb.getId());
                });
        }

        return userAccountDao.create(userAccount);
    }

    @Override
    public List<UserAccount> listAll() {

        return userAccountDao.listAll();
    }

    @Override
    public UserAccount updateUserAccount(UserAccount userAccount) {

        return userAccountDao.update(userAccount);
    }

    @Override
    public UserAccount fetchAccount(long id) {

        return userAccountDao.find(id);
    }

    @Override
    public UserAccount fetchAccount(String userName) {
        return userAccountDao.findByUsername(userName);
    }

    @Override
    public List<UserAccount> findAllNonDeleted() {

        return userAccountDao.findAllNotDeleted();
    }
}
