package com.blackbatsoft.web.auth.base;

import java.util.Set;

/**
 * Created by sunb on 2017/7/7.
 * 登录用户数据访问接口
 */
public interface IShiroService {

    // 根据登录名取得信息
    public IUser findByLoginName(Object loginName);

    // 根据登录名取得用户的角色
    Set<String> getUserRoles(Object loginName);

    // 根据登录名取用户权限
    Set<String> getPermissions(Object loginName);
}
