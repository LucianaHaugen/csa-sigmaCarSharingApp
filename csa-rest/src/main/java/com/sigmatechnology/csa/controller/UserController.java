package com.sigmatechnology.csa.controller;

import com.sigmatechnology.csa.entity.user.User;
import com.sigmatechnology.csa.service.UserService;
import com.sigmatechnology.csa.util.MappingsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lucianahaugen on 05/09/17.
 */

@RestController
public class UserController extends AbstractController{

    @Autowired
    private UserService userService;

    /**
     * Mehtod to list all Users, even those marked as deleted.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    @ResponseBody
    public List<User> listAll(){
        List<User> users = this.userService.listAll();
        System.out.println("list of users requested from User Service in the UserController");
        return users;
    }
}
