package com.stormbroken.swcontest.entity;
import lombok.Data;

@Data
public class userTaste {
    private String token;
    private boolean valid;
    private int type;
    private String addition;
}
