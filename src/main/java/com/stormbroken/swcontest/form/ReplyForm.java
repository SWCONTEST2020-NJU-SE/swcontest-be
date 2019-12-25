package com.stormbroken.swcontest.form;
import lombok.Data;

@Data
public class ReplyForm {
    private String token;
    private int id;
    private String content;
    private String url;
}
