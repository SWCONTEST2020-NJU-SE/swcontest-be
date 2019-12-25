package com.stormbroken.swcontest.vo;

import com.stormbroken.swcontest.entity.Comment;
import com.stormbroken.swcontest.entity.Cookbook;
import com.stormbroken.swcontest.entity.Steps;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class GetListVO {
    Object data;
    private Cookbook cookbook;
    private Comment comment;
    private Steps steps;
}
