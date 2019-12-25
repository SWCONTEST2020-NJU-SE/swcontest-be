package com.stormbroken.swcontest.form;

import lombok.Data;

@Data
public class UserEditForm {
    private String token;
    private String name;
    private String content;
    private String url;
}
