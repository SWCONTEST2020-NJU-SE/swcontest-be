package com.stormbroken.swcontest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordVO {
    private String username;
    private String password;
}
