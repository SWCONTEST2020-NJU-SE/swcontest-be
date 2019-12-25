package com.stormbroken.swcontest.vo;

import com.stormbroken.swcontest.entity.userTaste;
import com.stormbroken.swcontest.form.TasteForm;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class UserVO {
    //VO是用来给前端返回数据的
    private int id;
    private String name;
    private String token;
    private ArrayList<userTaste> taste;

}
