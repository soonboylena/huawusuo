package com.blackbatsoft.web.controller;

import com.blackbatsoft.web.auth.SessionUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunb on 2017/7/8.
 */
@Controller
public class LoginController {

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam("username") String username,
                                     @RequestParam("password") String password) {
        Subject currentUser = SecurityUtils.getSubject();
        String result;
        String message = "";
        String url = "/";
        if (!currentUser.isAuthenticated()) {

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(false);
            try {
                currentUser.login(token);
                result = "success";
            } catch (UnknownAccountException uae) {
                result = "failure";
                message = "不存在的账号。";
            } catch (IncorrectCredentialsException ice) {
                result = "failure";
                message = "密码不对。";
            } catch (LockedAccountException lae) {
                result = "failure";
                message = "该帐号已经被锁定，无法登录。";
            } catch (AuthenticationException ae) {
                result = "failure";
                message = "发生了一些人类无法理解的情况....总之，目前无法登录。";
            }
        } else {//重复登录
            result = "redirect";
            url = "/";
        }
        Map<String, Object> map = new HashMap<>(5);
        map.put("result", result);
        map.put("url", url);
        map.put("message", message);

        return map;
    }

    @GetMapping("/")
    public String home(ModelMap map) {

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            map.put("authenticated", true);
            //
            SessionUser user = (SessionUser) subject.getPrincipal();
            map.put("username", user.getName());

            map.put("permission1", subject.isPermitted("leftMenu:menu1"));
            map.put("permission2", subject.isPermitted("leftMenu:menu2"));
            map.put("permission3", subject.isPermitted("leftMenu:menu3"));

        }

        return "index";
    }

    @GetMapping("/logout")
    public String logout(ModelMap map) {

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "redirect:/";
    }
}
