package com.stormbroken.swcontest.vo;

import com.stormbroken.swcontest.entity.Cookbook;
import lombok.Data;

@Data
public class GetVO {
    private int code;
    private int score;
    private long createtime;
    private Cookbook commentbook;
    private String methods;

}
