package com.blackbatsoft.controller;

import com.blackbatsoft.model.User;
import com.blackbatsoft.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sunb on 17-6-27.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //@Autowired
    //private DiscoveryClient discoveryClient;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/find/{id}")
    public User getUser(@PathVariable Long id) {
        logger.info("method: {}", "getUser");
        return this.userRepository.findOne(id);
    }

    /**
     * 根据用户登录名取得用户信息
     * @param loginName
     * @return
     */
    @GetMapping("/findByLoginName/{loginName}")
    public User getUserVLoginName(@PathVariable String loginName) {
        logger.info("method: {}, loginName : {}", "getUserVLoginName", loginName);
        return this.userRepository.findUserByLoginName(loginName);
    }

    @GetMapping("/list")
    public List<User> getAllUser() {
        logger.info("method: {}", "getUser");
        return this.userRepository.findAll();
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        logger.info("method: {}", "saveUser");
        return this.userRepository.save(user);
    }
}
