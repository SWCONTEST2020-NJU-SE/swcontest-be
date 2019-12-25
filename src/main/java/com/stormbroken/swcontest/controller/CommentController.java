package com.stormbroken.swcontest.controller;

import com.stormbroken.swcontest.constant.SimpleResponse;
import com.stormbroken.swcontest.dao.CommentDao;
import com.stormbroken.swcontest.form.CommentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//控制层
@RequestMapping("/comment")

public class CommentController {
    @Autowired
    CommentDao commentDao;
    @RequestMapping ("/reply")
    public SimpleResponse Reply(CommentForm commentForm) {

    }

}
