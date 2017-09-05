package com.sigmatechnology.csa.util;

import com.google.common.collect.Lists;
import com.sigmatechnology.csa.entity.account.UserAccount;
import com.sigmatechnology.csa.entity.account.UserRole;
import com.sigmatechnology.csa.entity.user.User;
import com.sigmatechnology.csa.json.AbstractJsonObject;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by lucianahaugen on 05/09/17.
 */
public class MappingsUtil {

    public static List<User> mapUserAccountToUser(List<UserAccount> userAccounts) {
        List<User> returnList = new ArrayList<>();
        for (UserAccount userAccount : userAccounts) {
            returnList.add(mapUserAccountToUser(userAccount));
        }
        return returnList;
    }

    public static User mapUserAccountToUser(UserAccount userAccount) {
        User user = new User();
        user.setId(userAccount.getId());
        user.setUserName(userAccount.getUsername());
        user.setRoles(mapUserRolesToJson(userAccount.getRoles()));

        return user;
    }

    public static UserAccount mapUserToUserAccount(User user, UserAccount userAccount, List<User> users) {
        if (userAccount == null) {
            userAccount = new UserAccount();
        }
        userAccount.setId(user.getUserId());
        userAccount.setUsername(user.getUserName());
        /**
         * TODO: Take a look on sha256Hex
         */
        //userAccount.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        userAccount.setRoles(mapUserRolesToDao(user.getRoles()));

        return userAccount;
    }

    /**
     * TODO: Fix user
     */
   /* public static List<User> mapUsersToUsers(List<User> users) {
        List<User> returnList = new ArrayList<>();
        for (User user : users) {
            returnList.add(mapUserAccountToUser(user));
        }
        return returnList;
    }*/

    /**
     * Map Entity User to JSON User
     */
    public static User mapUserToUser(com.sigmatechnology.csa.entity.user.User user) {
        User jsonUser = new User();
        jsonUser.setId(user.getId());
        jsonUser.setUserName(user.getUserName());

        return jsonUser;
    }

    private static List<UserRole> mapUserRolesToJson(List<UserRole> userRolesDao){
        List<UserRole> userRolesJson = Lists.newArrayList();

        if(Objects.nonNull(userRolesDao)){
            userRolesDao.forEach((role -> {
                UserRole userRole = new UserRole();
                userRole.setRole(role.getRole());
                userRolesJson.add(userRole);

            }));
        }
        return userRolesJson;
    }

    private static List<UserRole> mapUserRolesToDao(List<UserRole> userRolesJson){
        List<UserRole> userRolesDao = Lists.newArrayList();

        if(Objects.nonNull(userRolesJson)){
            userRolesJson.forEach((role) -> {
                UserRole userRole = new UserRole();
                userRole.setRole(role.getRole());
                userRolesDao.add(userRole);
            });
        }

        return userRolesDao;
    }

    public static List<Long> mapIdList(List<? extends AbstractJsonObject> jsonList){
        List<Long> idList = new ArrayList<>();
        for(AbstractJsonObject jsonObject : jsonList){
            idList.add(jsonObject.getId());
        }

        return idList;
    }



}
