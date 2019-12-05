package com.stormbroken.swcontest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stormbroken.swcontest.SwcontestApplication;
import com.stormbroken.swcontest.constant.ResponseCode;
import com.stormbroken.swcontest.constant.SimpleResponse;
import com.stormbroken.swcontest.dao.LogDao;
import com.stormbroken.swcontest.dao.UserDao;
import com.stormbroken.swcontest.entity.Edit;
import com.stormbroken.swcontest.entity.Log;
import com.stormbroken.swcontest.entity.User;
import com.stormbroken.swcontest.form.EditForm;
import com.stormbroken.swcontest.form.LoginForm;
import com.stormbroken.swcontest.form.RegisterForm;
import com.stormbroken.swcontest.utils.MD5Encryption;
import com.stormbroken.swcontest.vo.UserVO;
import com.stormbroken.swcontest.constant.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    UserDao userDao;

    @Autowired
    LogDao logDao;

    @RequestMapping("/register")
    public SimpleResponse register(@RequestBody RegisterForm registerForm){
        System.out.println("一个用户进行注册操作");
        if(userDao.findByName(registerForm.getName())==null){
            try {
                registerForm.setPassword(MD5Encryption.encrypt(registerForm.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw new ServerException(ResponseCode.Error,"Encode error");
            }
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
        User tempUser=userDao.findByName(loginForm.getName());
        String password = loginForm.getPassword();
        try{
            password = MD5Encryption.encrypt(password);
        }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
            throw new ServerException(ResponseCode.Error,"Encode error");
        }
        if(password.equals(tempUser.getPassword())){
            System.out.println(tempUser+"成功登陆");
            //生成32位随机String也就是token
            String string= UUID.randomUUID().toString().replace("-","");

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
            User user = SwcontestApplication.findAccountByToken(editForm.getToken());
            String username = user.getName();
            if(userDao.findByName(editForm.getName())==null||editForm.getName().equals(username)){
                Edit edit = new Edit(username,editForm);
                userDao.updateUser(edit);
                System.out.println("成功修改账户信息"+edit);
                this.writeLog(user.getId(),editForm,new SimpleResponse(0));
                return new SimpleResponse(0);
            }else{
                System.out.println("用户名被占用");
                return new SimpleResponse(2);
            }

        }catch(Exception ex){
            return new SimpleResponse(1,ex);
        }
    }

    public void writeLog(int uid,Object form1,Object form2){
        try{
            System.out.println("开始日志写入");
            String request = objectMapper.writeValueAsString(form1);
            String response = objectMapper.writeValueAsString(form2);
            Log log = new Log(uid,request,response);
            logDao.insert(log);
            System.out.println("完成日志写入");
        }catch(JsonProcessingException ex){
            ex.printStackTrace();
        }
    }
}
