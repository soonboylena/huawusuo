package com.blackbatsoft.web.auth.base;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by sunb on 2017/7/7.
 * 一般来说这个类不用动。变化的部分都在IUserDao里边，通过不同的IUserDao的实现类进行业务处理
 * 权限\角色都使用string格式
 */
public class WebRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(WebRealm.class);

    @Autowired
    private IShiroService service;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        IUser user = (IUser) super.getAvailablePrincipal(principalCollection);
        String loginName = (String) user.getUserName();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> userRoles = service.getUserRoles(loginName);
        if (userRoles != null) {
            info.setRoles(userRoles);
            // log
            if (logger.isDebugEnabled()) {
                logger.debug("用户的登录名：{},角色如下：", loginName);
                for (String userRole : userRoles) {
                    logger.debug("  --- {}", userRole);
                }
                logger.debug("-----------------------");
            }
        } else {
            logger.debug("用户角色信息为null。用户的登录名：{}", loginName);
        }

        Set<String> permissions = service.getPermissions(loginName);
        if (permissions != null) {
            info.setStringPermissions(permissions);
            // log
            if (logger.isDebugEnabled()) {
                logger.debug("用户的登录名：{},权限如下：", loginName);
                for (String permission : permissions) {
                    logger.debug("  --- {}", permission);
                }
                logger.debug("-----------------------");
            }
        } else {
            logger.debug("用户权限信息为null。用户的登录名：{}", loginName);
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户账号
        String username = authenticationToken.getPrincipal().toString();
        IUser user = service.findByLoginName(username);
        if (user != null) {
            logger.debug("取得用户凭证：{}", user.getUserName());
            return new SimpleAuthenticationInfo(user, user.getCredentials(), getName());
        } else {
            return null;
        }
    }
}
