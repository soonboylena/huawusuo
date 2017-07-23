package com.blackbatsoft.repository;

import com.blackbatsoft.UserApplication;
import com.blackbatsoft.model.Permission;
import com.blackbatsoft.model.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunb on 2017/7/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class RoleRepositoryTest {


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void saveRole() {


        List<Role> roles = new ArrayList<>();

        Role role = new Role();
        role.setRoleName("管理员");
        role.setSignal("admin");
        role.setDescription("测试插入数据:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        roles.add(role);

        Role role1 = new Role();
        role1.setRoleName("普通用户");
        role1.setSignal("user1");
        role1.setDescription("测试插入数据:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        roles.add(role1);

        List<Role> save = roleRepository.save(roles);

        Assert.assertNotNull(save);

    }

    @Test
    public void savePermission() {

        List<Permission> permissionList = new ArrayList<>();


        Permission permission = new Permission();
        permission.setPermissonName("完全权限");
        permission.setPermissonSign("*");
        permission.setDescription("测试插入数据：" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        permissionList.add(permission);

        Permission permission1 = new Permission();
        permission1.setPermissonName("菜单1权限");
        permission1.setPermissonSign("leftMenu:menu1");
        permission1.setDescription("测试插入数据：" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        permissionList.add(permission1);

        Permission permission2 = new Permission();
        permission2.setPermissonName("菜单2权限");
        permission2.setPermissonSign("leftMenu:menu2");
        permission2.setDescription("测试插入数据：" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        permissionList.add(permission2);

        Permission permission3 = new Permission();
        permission3.setPermissonName("菜单3权限");
        permission3.setPermissonSign("leftMenu:menu3");
        permission3.setDescription("测试插入数据：" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        permissionList.add(permission3);

        List<Permission> saves = permissionRepository.save(permissionList);

        Assert.assertNotNull(saves);
    }

}