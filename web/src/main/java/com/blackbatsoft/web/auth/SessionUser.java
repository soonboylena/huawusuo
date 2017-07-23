package com.blackbatsoft.web.auth;

import com.blackbatsoft.web.auth.base.IUser;

/**
 * Created by sunb on 2017/7/9.
 */
public class SessionUser implements IUser {

    private String userName;
    private String credentials;
    private String name;
    private Long userId;

    @Override
    public Object getUserName() {
        return userName;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
