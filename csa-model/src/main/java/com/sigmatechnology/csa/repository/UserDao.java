package com.sigmatechnology.csa.repository;


import com.sigmatechnology.csa.entity.user.User;

import java.util.List;

/**
 * Created by lucianahaugen on 31/08/17.
 */
public interface UserDao extends CrudDao<User> {

    public List<User> findByIds(List<Long> ids);
    public List<User> fetchAll(List<Long> ids);
    public List<User> fetchByNames(List<String> userNames);


}