package com.sigmatechnology.csa.service;

import com.sigmatechnology.csa.entity.account.UserAccount;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lucianahaugen on 04/09/17.
 */
@Transactional
public interface UserAccountService {

    UserAccount createAccount(UserAccount userAccount);
    List<UserAccount> listAll();
    UserAccount updateUserAccount(UserAccount userAccount);
    UserAccount fetchAccount(long id);
    UserAccount fetchAccount(String userName);
    List<UserAccount> findAllNonDeleted();
}
