package com.stormbroken.swcontest.form;

import lombok.Data;

@Data
public class LoginForm {
    private  int id;
    private int isLogin;
    private String name;
    private String password;
}
