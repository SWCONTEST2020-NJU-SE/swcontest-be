package com.stormbroken.swcontest.entity;
import lombok.Data;

@Data
public class userTaste {
    private int tasteId;
    private int uid;
    private String token;
    private boolean valid;
    private int type;
    private String addition;
}
