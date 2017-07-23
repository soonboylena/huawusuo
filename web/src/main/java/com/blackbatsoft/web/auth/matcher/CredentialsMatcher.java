package com.blackbatsoft.web.auth.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sunb on 2017/7/8.
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    private static final Logger logger = LoggerFactory.getLogger(CredentialsMatcher.class);

    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        String inPassword = new String(utoken.getPassword());
        String dbPassword = (String) info.getCredentials();

        boolean result = this.equals(inPassword, dbPassword);
        if (!result) {
            logger.info("密码不符。用户名：{}", utoken.getPassword());
        }
        return this.equals(inPassword, dbPassword);
    }
}
