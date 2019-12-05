package com.stormbroken.swcontest.controller;

import com.stormbroken.swcontest.SwcontestApplication;
import com.stormbroken.swcontest.constant.SimpleResponse;
import com.stormbroken.swcontest.dao.UserDao;
import com.stormbroken.swcontest.entity.User;
import com.stormbroken.swcontest.form.EditForm;
import com.stormbroken.swcontest.form.LoginForm;
import com.stormbroken.swcontest.form.RegisterForm;
import com.stormbroken.swcontest.vo.EditVO;
import com.stormbroken.swcontest.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/register")
    public SimpleResponse register(@RequestBody RegisterForm registerForm){
        System.out.println("一个用户进行注册操作");
        if(userDao.findByName(registerForm.getName())==null){
            userDao.addUser(registerForm);
            System.out.println("成功创建账户"+registerForm);
            return new SimpleResponse(0);
        }
        System.out.println("用户名被占用");
        return new SimpleResponse(1);
    }

    @RequestMapping("/login")
    public SimpleResponse login(@RequestBody LoginForm loginForm){
        System.out.println("一个用户进行登陆操作");
        User tempUser=userDao.findByNameAndPassword(loginForm);
        if(tempUser!=null){
            System.out.println(tempUser+"成功登陆");
            //生成32位随机String
            String string= UUID.randomUUID().toString().replace("-","");  //32位随机String
            System.out.println(string);

            SwcontestApplication.tokenMap.put(string,tempUser);

            UserVO userVO = new UserVO(tempUser.getId(),tempUser.getName(),string);
            return new SimpleResponse(0,userVO);
        }
        return new SimpleResponse(1);
    }

    @RequestMapping("/edit")
    public SimpleResponse edit(@RequestBody EditForm editForm){
        try{
            System.out.println("一个用户进行修改用户信息操作");
            if(userDao.findByName(editForm.getName())==null){
                String token = editForm.getToken();
                String username = SwcontestApplication.findAccountByToken(token).getName();
                EditVO editVO = new EditVO(username,editForm);
                userDao.updateUser(editVO);
                return new SimpleResponse(0);
            }else{
                System.out.println("用户名被占用");
                return new SimpleResponse(2);
            }

        }catch(Exception ex){
            return new SimpleResponse(1,ex);
        }
    }
}
