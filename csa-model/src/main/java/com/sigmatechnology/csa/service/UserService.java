package com.sigmatechnology.csa.service;

import com.sigmatechnology.csa.entity.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */

/**
 @Transactional annotation is required to get rid of error message:
 'org.springframework.dao.InvalidDataAccessApiUsageException: No EntityManager
 with actual transaction available for current thread - cannot reliably process
 'remove' call.'
 */

@Transactional
public interface UserService {
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(User user);
    List<User> listAll();
    User fetchUser(long id);
}
