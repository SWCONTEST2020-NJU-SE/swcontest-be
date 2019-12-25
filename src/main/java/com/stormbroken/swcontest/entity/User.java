package com.stormbroken.swcontest.entity;

import com.stormbroken.swcontest.form.TasteForm;
import lombok.Data;

import java.util.ArrayList;

@Data
public class User {
    private int id;
    private String menu;
    private String name;
    private int category;
    private String password;
    private long createtime;
    private String content;
    private String url;
    private String likes;
    private String history;
    private  int tasteId;
    private ArrayList<userTaste> taste;

}
