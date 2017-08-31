package com.sigmatechnology.csa.service;

import com.sigmatechnology.csa.entity.user.User;
import com.sigmatechnology.csa.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User createUser(User user) {
        return userDao.create(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(userDao.find(user.getId()));
    }

    @Override
    public List<User> listAll() {
        return userDao.listAll();
    }

    @Override
    public User fetchUser(long id) {
        return userDao.find(id);
    }
}
