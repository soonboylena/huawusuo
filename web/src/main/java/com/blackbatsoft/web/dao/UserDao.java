package com.blackbatsoft.web.dao;

import com.blackbatsoft.web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singletonMap;

/**
 * Created by sunb on 2017/7/9.
 */

@Component
public class UserDao extends WebBaseDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    @Value("${userService.key}")
    private String serviceKey;


    public User findUserByLoginName(String loginName) {

        //  自己调用template的写法
        //        URI url = url("findByLoginName/{loginName}", Collections.singletonMap("loginName", loginName));
        //        ResponseEntity<User> responseEntity = template.getForEntity(url, User.class);
        //        return responseEntity.getBody();

        // 或者调用基类提供的方法
        return super.getEntity("findByLoginName/{loginName}", Collections.singletonMap("loginName", loginName), User.class);
    }


    @Override
    public String getServiceKey() {
        return serviceKey;
    }


    public Set findRolesByLoginName(String loginName) {

        return super.getEntity("roleStrings/{loginName}", Collections.singletonMap("loginName", loginName), Set.class);
    }

    public Set findPermissionStringsByLoginName(String loginName) {
        return super.getEntity("permissionStrings/{loginName}", Collections.singletonMap("loginName", loginName), Set.class);
    }
}
