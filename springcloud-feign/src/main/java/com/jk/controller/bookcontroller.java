package com.jk.controller;

import com.jk.model.HomestayMdel;
import com.jk.model.ImgModel;
import com.jk.model.UserMdel;
import com.jk.service.booksercice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("b")
@Api(value = "aaa")
public class bookcontroller {
    @Autowired
    private booksercice bksercice;

   @RequestMapping("query")
   @ApiOperation(value = "测试传参")
    public  void query() {
        bksercice.queryCar("少时诵诗书所所所所");
    }
    @GetMapping("log")
    public  String log(String username, String pwd, HttpSession sessionession) {

    UserMdel user= bksercice.log(username,pwd);

    if (user!=null){

        sessionession.setAttribute("user",user);

        return "登录成功";
    }

    else {

        return "账号或密码错误";
    }


    }
    @GetMapping("queryzhu")
    public List<HomestayMdel> queryzhu() {
        return bksercice.queryzhu();
    }

    @GetMapping("queryge")
    public UserMdel queryge(String id) {


        return bksercice.queryge(id);
    }
    @PostMapping("add")
    public void add(UserMdel user) {


        bksercice.add(user);
    }
    @GetMapping("queryimg")
    public List<ImgModel> queryimg(String id) {
        return bksercice.queryimg(id);
    }





}
