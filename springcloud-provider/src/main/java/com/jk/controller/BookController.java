package com.jk.controller;

import com.jk.model.HomestayMdel;
import com.jk.model.ImgModel;
import com.jk.model.UserMdel;
import com.jk.service.Bookserver;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class BookController implements Bookserver {
    @Autowired
private UserService userService;
    @Override
    public void queryCar(String bookname) {
        System.out.println(bookname);
    }

    @Override
    public UserMdel log(String username, String pwd) {

        return userService.log(username,pwd);
    }

    @Override
    public List<HomestayMdel> queryzhu() {
        return userService.queryzhu();
    }

    @Override
    public UserMdel queryge(String id) {
        return userService.queryge(id);
    }

    @Override
    public void add(@RequestBody UserMdel user) {
        userService.add(user);
    }

    @Override
    public HomestayMdel queryzhu2(String id) {
        return userService.queryzhu2(id);
    }

    @Override
    public List<ImgModel> queryimg(String id) {
        return userService.queryimg(id);
    }


    public static void main(String[] args) {

        System.out.println( Math.round(-1.5));
    }
}
