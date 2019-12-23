package com.stormbroken.swcontest.form;

import lombok.Data;

@Data//自动给所有属性加get和set
public class RegisterForm {
    private String name;
    private String password;
    private long createtime;
    private String content;
    private String url;
}
