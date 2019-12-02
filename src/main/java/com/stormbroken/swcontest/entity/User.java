package com.stormbroken.swcontest.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private int category;
    private String password;
    private long createtime;
    private String content;
    private String friendIDs;
    private String ban;
    private String likes;
    private String history;
}
