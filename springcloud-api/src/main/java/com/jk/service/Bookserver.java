package com.jk.service;

import com.jk.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

public interface Bookserver {
    @GetMapping("b/queryName")
    void queryCar(@RequestParam("bookname") String bookname);
    @GetMapping("b/log")
    UserMdel log(@RequestParam("username") String username,@RequestParam("pwd") String pwd);
    @GetMapping("b/queryzhu")
    List<HomestayMdel> queryzhu();

    @GetMapping("b/queryge")
    UserMdel queryge(@RequestParam("id") String id);
    @PostMapping("b/add")
    void add(@RequestBody UserMdel user);
    @PostMapping("b/queryzhu2")
    HomestayMdel queryzhu2(@RequestParam("id") String id);
    @GetMapping("b/queryimg")
    List<ImgModel> queryimg(@RequestParam("id") String id);
    @GetMapping("b/queryxiao")
    List<xiaoModel> queryxiao(String id);
    @PostMapping("b/queryCommentById")
    List<Comment> queryCommentById(@RequestParam("commentid") String commentid,@RequestParam("pid") String pid);
    @PostMapping("b/queryPraise")
    List<Praise> queryPraise(@RequestParam("userid") Integer userid, @RequestParam("id") String id);
    @PostMapping("b/delPraise")
    void delPraise(@RequestParam("id") String id);
    @PostMapping("b/addPraise")
    void addPraise(@RequestParam("userid") Integer userid,@RequestParam("id") String id);
    @PostMapping("b/addhome")
    void addhome(@RequestBody HomestayMdel homepojo);
}
