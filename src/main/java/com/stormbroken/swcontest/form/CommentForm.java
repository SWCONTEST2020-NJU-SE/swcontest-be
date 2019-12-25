package com.stormbroken.swcontest.form;
import lombok.Data;

@Data
public class CommentForm {
    private String token;
    private int id;
    private String content;
    private String url;
}
