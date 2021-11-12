package com.lamp.device.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo1")
public class Demo1Controller {

    @RequestMapping("hello_world")
    @ResponseBody
    public String helloWorld(){
        return "hello world";
    }
}
