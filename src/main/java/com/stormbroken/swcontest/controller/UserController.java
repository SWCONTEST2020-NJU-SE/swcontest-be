package com.stormbroken.swcontest.controller;

import com.stormbroken.swcontest.constant.SimpleResponse;
import com.stormbroken.swcontest.dao.UserDao;
import com.stormbroken.swcontest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/register")
    public SimpleResponse register(@RequestBody User user){
        System.out.println("一个用户进行注册操作");
        if(userDao.findByName(user.getName())==null){
            userDao.addUser(user);
            System.out.println("成功创建账户"+user);
            return new SimpleResponse(0);
        }
        System.out.println("用户名被占用");
        return new SimpleResponse(1);
    }
}
