package com.stormbroken.swcontest.entity;

import com.stormbroken.swcontest.form.UserEditForm;
import lombok.Data;

@Data
public class Edit {
    private String username;
    private String token;
    private String name;
    private String content;
    private String url;
    public Edit(String username, UserEditForm userEditForm){
        this.username = username;
        this.token = userEditForm.getToken();
        this.name = userEditForm.getName();
        this.content = userEditForm.getContent();
        this.url = userEditForm.getUrl();
    }
}
