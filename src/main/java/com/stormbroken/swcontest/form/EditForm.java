package com.stormbroken.swcontest.form;

import lombok.Data;

@Data
public class EditForm {
    private String token;
    private String name;
    private String content;
    private String url;
}
