package com.stormbroken.swcontest.form;

import com.stormbroken.swcontest.entity.Steps;
import lombok.Data;

@Data
public class CookbookEditForm {
    private String token;
    private int id;
    private String name;
    private String category;
    private String material;
    private int calorie;
    private int salt;
    private int sugar;
    private  int protein;
    private int fat;
    private String url;
    private int  steps;
}
