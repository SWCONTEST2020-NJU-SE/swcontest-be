package com.stormbroken.swcontest.form;
import lombok.Data;

@Data
public class TasteForm {
    private String token;
    private boolean valid;
    private int type;
    private String addition;
}
