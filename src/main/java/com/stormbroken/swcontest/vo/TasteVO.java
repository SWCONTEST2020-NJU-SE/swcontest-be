package com.stormbroken.swcontest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TasteVO {
    private String token;
    private boolean valid;
    private int type;
    private String addition;
}

