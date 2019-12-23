package com.stormbroken.swcontest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stormbroken.swcontest.SwcontestApplication;
import com.stormbroken.swcontest.constant.ResponseCode;
import com.stormbroken.swcontest.constant.SimpleResponse;
import com.stormbroken.swcontest.dao.LogDao;
import com.stormbroken.swcontest.dao.UserDao;
import com.stormbroken.swcontest.entity.Edit;
import com.stormbroken.swcontest.entity.Log;
import com.stormbroken.swcontest.entity.User;
import com.stormbroken.swcontest.form.*;
import com.stormbroken.swcontest.utils.MD5Encryption;
import com.stormbroken.swcontest.vo.ChangePasswordVO;
import com.stormbroken.swcontest.vo.UserVO;
import com.stormbroken.swcontest.constant.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController//控制层
@RequestMapping("/user")//域名
public class UserController {


    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired//自动装配
    UserDao userDao;//持久层

    @Autowired
    LogDao logDao;


    @RequestMapping("/register")
    public SimpleResponse register(@RequestBody RegisterForm registerForm){//注册形式
        System.out.println("一个用户进行注册操作");
        if (userDao.findByName(registerForm.getName()) == null) {//重名问题
            registerForm.setPassword(registerForm.getPassword());
            userDao.addUser(registerForm);
            System.out.println("成功创建账户" + registerForm);//看一下前端传的数据对不对
            return new SimpleResponse(0);//没有问题，其他的有问题
        }
            System.out.println("用户名被占用");
            return new SimpleResponse(1);
    }

    @RequestMapping("/login")
    public SimpleResponse login(@RequestBody LoginForm loginForm){
        System.out.println("一个用户进行登陆操作");
        User tempUser=userDao.findByName(loginForm.getName());//先找用户，再找密码
        String password = loginForm.getPassword();
        try{
            password = MD5Encryption.encrypt(password);
        }catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
            throw new ServerException(ResponseCode.Error,"Encode error");
        }
        if(password.equals(tempUser.getPassword())){//输入正确
            System.out.println(tempUser+"成功登陆");
            //生成32位随机String也就是token
            String string= UUID.randomUUID().toString().replace("-","");

            SwcontestApplication.tokenMap.put(string,tempUser);
            UserVO userVO = new UserVO(tempUser.getId(),tempUser.getName(),string);
            return new SimpleResponse(0,userVO);
        }
        return new SimpleResponse(1);
    }
    @RequestMapping("/me")
    public SimpleResponse me(@RequestBody TokenForm tokenForm){
        try {
            String token=tokenForm.getToken();
            User user=SwcontestApplication.findAccountByToken(token);
            return new SimpleResponse(0,user);   //直接给对象是可以的 data里是json格式
        }catch (Exception e){
            return new SimpleResponse(1,e);
        }
    }
    @RequestMapping("/edit")
    public SimpleResponse edit(@RequestBody EditForm editForm){//前端传过来的文件
        try{
            System.out.println("一个用户进行修改用户信息操作");
            User user = SwcontestApplication.findAccountByToken(editForm.getToken());
            if(user == null){
                System.out.println("查无此人");
                return new SimpleResponse(1,editForm);
            }
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
    @RequestMapping("/chapa")
    public SimpleResponse changePassword(@RequestBody ChangePasswordForm changePasswordForm){
        System.out.println("一个用户进行修改密码操作");
        String token =changePasswordForm.getToken();
        String oldpassword=changePasswordForm.getOldpassword();
        String newpassword= changePasswordForm.getNewpassword();
        String username=SwcontestApplication.findAccountByToken(token).getName();
        if(userDao.findByNameAndPassword(username,oldpassword)!=null){
            userDao.changePassword(new ChangePasswordVO(username,newpassword));
            System.out.println("成功修改密码为"+newpassword);
            return new SimpleResponse(0);
        }
        else {
            System.out.println("用户名不正确或密码错误");
            return new SimpleResponse(1);  //有错误
        }
    }
    @RequestMapping("/taste")
    public SimpleResponse taste(@RequestBody TasteForm tasteForm ){
        System.out.println("修改一个用户的口味");
        String token=tasteForm.getToken();
        boolean Create=tasteForm.isValid();
        return new SimpleResponse(0);

    }
    @RequestMapping("/logout")
    public  SimpleResponse logout(@RequestBody TokenForm  tokenForm){
        System.out.println("一个用户进行登出操作");
        try{
            String token=tokenForm.getToken();
            String useName = SwcontestApplication.findAccountByToken(token).getName();
            User user=userDao.findByName(useName);
            System.out.println(user+"已下线");
            return new SimpleResponse(0,user);
        }catch (Exception e){
            return new SimpleResponse(1,e);
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
