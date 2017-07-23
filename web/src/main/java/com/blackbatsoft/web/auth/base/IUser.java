package com.blackbatsoft.web.auth.base;

/**
 * Created by sunb on 2017/7/7.
 * 用户接口
 * 实例需序列化
 */
public interface IUser {

    Object getUserName();

    Object getCredentials();
}
