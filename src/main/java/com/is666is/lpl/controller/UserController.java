package com.is666is.lpl.controller;

import com.is666is.lpl.mq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MessageSender messageSender;
    @RequestMapping("/send/{str}")
    @ResponseBody
    public String send(@PathVariable("str") String str){

        return messageSender.send("exchange1","k1",str);
    }
}
