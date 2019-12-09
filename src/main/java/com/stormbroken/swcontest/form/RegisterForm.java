package com.stormbroken.swcontest.form;

import lombok.Data;

@Data
public class RegisterForm {
    private String name;
    private String password;
    private long createtime;
    private String content;
    private String url;
}
