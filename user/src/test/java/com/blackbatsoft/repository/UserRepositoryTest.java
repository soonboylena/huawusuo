package com.blackbatsoft.repository;

import com.blackbatsoft.UserApplication;
import com.blackbatsoft.model.Role;
import com.blackbatsoft.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Created by sunb on 2017/7/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@ActiveProfiles("test")
public class UserRepositoryTest {


    @Autowired
    UserRepository userRepository;

    @Test
    public void getUserByLoginName() throws Exception {
        User one = userRepository.findOne(1L);
        Set<Role> roles = one.getRoles();

        Assert.assertTrue(roles == null);
    }

    @Test
    public void insertUser() {

        List<User> users = new ArrayList<>(5);
        User admin = new User();
        admin.setLoginName("admin");
        admin.setAddress("地址0");
        admin.setAge(25);
        admin.setName("张老六");
        admin.setPassword("admin");
        admin.setSex(0);
        users.add(admin);

        User user1 = new User();
        user1.setLoginName("user1");
        user1.setAddress("地址1");
        user1.setAge(26);
        user1.setName("朴一生");
        user1.setPassword("user1");
        user1.setSex(0);
        users.add(user1);

        User user2 = new User();
        user2.setLoginName("user2");
        user2.setAddress("地址2");
        user2.setAge(26);
        user2.setName("朴人猛");
        user2.setPassword("user2");
        user2.setSex(0);
        users.add(user2);
        List<User> save = userRepository.save(users);
        Assert.assertEquals(save.size(), 3);

    }
}