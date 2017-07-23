package com.blackbatsoft.controller;

import com.blackbatsoft.model.Permission;
import com.blackbatsoft.model.Role;
import com.blackbatsoft.model.User;
import com.blackbatsoft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sunb on 2017/7/13.
 */
@RestController
public class PermissionController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("permissions/{loginUserName}")
    public Set<Permission> getPermissionsByLoginName(@PathVariable(name = "loginUserName") String loginUserName) {

        User user = userRepository.findUserByLoginName(loginUserName);
        if (user == null) {
            return null;
        }

        Set<Role> roles = user.getRoles();

        Set<Permission> permissions = new HashSet<>();

        if (roles != null) {
            for (Role role : roles) {
                permissions.addAll(role.getPermissions());
            }
        }

        if (user.getPermissions() != null) {
            permissions.addAll(user.getPermissions());
        }

        return permissions;

    }

    /**
     * 根据用户登录名取得权限字符串
     * @param loginUserName
     * @return
     */
    @GetMapping("permissionStrings/{loginUserName}")
    public Set<String> getPermissionStringsByLoginName(@PathVariable(name = "loginUserName") String loginUserName) {

        Set<Permission> permissionSet = getPermissionsByLoginName(loginUserName);
        if (permissionSet == null) return null;
        return permissionSet.parallelStream().map(Permission::getPermissonSign).collect(Collectors.toSet());

    }
}
