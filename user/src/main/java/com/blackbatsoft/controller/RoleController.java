package com.blackbatsoft.controller;

import com.blackbatsoft.model.Role;
import com.blackbatsoft.model.User;
import com.blackbatsoft.repository.RoleRepository;
import com.blackbatsoft.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sunb on 2017/7/13.
 */
@RestController
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/role/save")
    public Role saveUser(@RequestBody Role role) {
        logger.info("method: {}", "save role");
        return this.roleRepository.save(role);
    }

    @GetMapping("/roles/{loginUserName}")
    public Set<Role> getRolesByLoginName(@PathVariable(name = "loginUserName") String loginName) {

        User user = userRepository.findUserByLoginName(loginName);
        if (user == null) return null;

        return user.getRoles();
    }

    /***
     * 返回权限字符串
     * @param loginName
     * @return
     */
    @GetMapping("/roleStrings/{loginUserName}")
    public Set<String> getRolesStringByLoginName(@PathVariable(name = "loginUserName") String loginName) {

        User user = userRepository.findUserByLoginName(loginName);
        if (user == null || user.getRoles() == null) return null;

        return user.getRoles().parallelStream().map(Role::getSignal).collect(Collectors.toSet());
    }
}
