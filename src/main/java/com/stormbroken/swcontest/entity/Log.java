package com.stormbroken.swcontest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Log {
    private int uid;
    private String request;
    private String response;
}
