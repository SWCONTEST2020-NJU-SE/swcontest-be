package com.stormbroken.swcontest.entity;

import com.stormbroken.swcontest.form.EditForm;
import lombok.Data;

@Data
public class Edit {
    private String username;
    private String token;
    private String name;
    private String content;
    private String url;
    public Edit(String username, EditForm editForm){
        this.username = username;
        this.token = editForm.getToken();
        this.name = editForm.getName();
        this.content = editForm.getContent();
        this.url = editForm.getUrl();
    }
}
