package com.sigmatechnology.csa.repository;

import com.sigmatechnology.csa.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */
@Repository
public class UserRepo extends CrudRepo<User> implements UserDao{

    @Override
    public List<User> findByIds(List<Long> ids) {
        return entityManager
                .createNamedQuery(User.FIND_BY_IDS, User.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public List<User> fetchAll(List<Long> ids) {
        return entityManager
                .createNamedQuery(User.FETCH_ALL, User.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public List<User> fetchByNames(List<String> userNames) {
        return entityManager
                .createNamedQuery(User.FETCH_BY_NAMES, User.class)
                .setParameter("names", userNames)
                .getResultList();
    }
}
