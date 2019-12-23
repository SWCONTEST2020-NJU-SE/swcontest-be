package com.stormbroken.swcontest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateVO {
    private String username;
    private String name;
    private String phone;
    private int grade;
    private String major;
}
