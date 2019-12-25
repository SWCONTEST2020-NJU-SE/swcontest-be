package com.stormbroken.swcontest.form;

import lombok.Data;

@Data
public class CrearteForm {
    private String token;
    private String cname;
    private String category;
    private String material;
    private int calorie;
    private int salt;
    private int sugar;
    private int protein;
    private int fat;
    private String url;
    private int steps;
}
