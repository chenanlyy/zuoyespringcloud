package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("p")
public class pagecontroller {
     @RequestMapping("log")
    public  String log() {
        return "log";
    }

    @RequestMapping("index")
    public  String index() {
        return "index";
    }
    @RequestMapping("show")
    public  String show() {
        return "show";
    }

    @RequestMapping("fang")
    public  String fang() {
        return "fang";
    }
}
