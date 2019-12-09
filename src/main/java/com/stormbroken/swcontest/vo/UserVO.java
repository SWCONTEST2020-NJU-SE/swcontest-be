package com.stormbroken.swcontest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserVO {
    //VO是用来给前端返回数据的
    private int id;
    private String name;
    private String token;
}
