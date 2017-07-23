package com.blackbatsoft.web.auth;

import com.blackbatsoft.web.auth.base.IUser;
import com.blackbatsoft.web.auth.base.IShiroService;
import com.blackbatsoft.web.model.User;
import com.blackbatsoft.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by sunb on 2017/7/7.
 */
@Component
public class RestShiroService implements IShiroService {

    @Autowired
    private UserDao userService;


    @Override
    public IUser findByLoginName(Object loginName) {
        User dbUser = userService.findUserByLoginName((String) loginName);
        if (dbUser != null) {
            return convertDbUser2SessionUser(dbUser);
        }
        return null;
    }

    private IUser convertDbUser2SessionUser(User dbUser) {
        SessionUser sessionUser = new SessionUser();
        sessionUser.setUserName(dbUser.getLoginName());
        sessionUser.setCredentials(dbUser.getPassword());
        sessionUser.setName(dbUser.getName());
        sessionUser.setUserId(dbUser.getId());
        return sessionUser;
    }

    @Override
    public Set<String> getUserRoles(Object loginName) {
        return userService.findRolesByLoginName((String) loginName);
    }

    @Override
    public Set<String> getPermissions(Object loginName) {
        return userService.findPermissionStringsByLoginName((String) loginName);
    }
}
