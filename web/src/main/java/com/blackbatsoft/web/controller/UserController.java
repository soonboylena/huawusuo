package com.blackbatsoft.web.controller;

import com.blackbatsoft.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunb on 2017/6/30.
 */


@RestController
public class UserController {


    @Autowired
    private RestTemplate template;

    private final static String gateway = "http://localhost:5000";
    private final static String userServiceId = "user";


    @PostMapping("addUser")
//    @CrossOrigin(origins = "*") //Demo示例用，实际使用要删除
    public boolean addUser(@RequestBody User user) {

        Map<String, Object> postData = new HashMap<>();
        postData.put("name", user.getName());
        postData.put("age", user.getAge());
        postData.put("address", user.getAddress());

        // 如果服务之前的user结构完全一样，可以直接传user---推荐
        // 如果有差别，传map。方便调整结构。
        String s = template.postForObject(gateway + "/" + userServiceId + "/save", postData, String.class);
        System.out.println(s);
        return true;
    }

    @GetMapping("allUser")
//    @CrossOrigin(origins = "*") //Demo示例用，实际使用要删除
    public User[] allUser() {
        String url = gateway + "/" + userServiceId + "/list";
        ResponseEntity<User[]> responseEntity = template.getForEntity(url, User[].class);
        return responseEntity.getBody();
        //return new ArrayList<>(0);
    }


}
