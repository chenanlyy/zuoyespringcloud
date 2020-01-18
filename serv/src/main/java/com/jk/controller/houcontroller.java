package com.jk.controller;

import com.jk.model.TreeBean;
import com.jk.model.UserModel;
import com.jk.service.houservicee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("h")
public class houcontroller {
    @Autowired
    private houservicee seStuservice;
    /*登录*/
    @RequestMapping("login")
    @ResponseBody
    public String login(UserModel user,String code,HttpSession session){


        //验证用户名
        UserModel user2 = seStuservice.queryUserName(user.getUsername());
        if(user2==null){
            return "用户名不存在！";

        }


        //验证密码
        if(!user.getPwd().equals(user2.getPwd())){
            return "密码错误！";
        }
        session.setAttribute("user", user2);
        return "登录成功！";
    }



    @RequestMapping("queryTree")
    @ResponseBody
    public List<TreeBean> queryTree(HttpSession session){
        return seStuservice.queryTree(session);
    }
    @RequestMapping("queryuser")
    @ResponseBody
    public HashMap<String,Object> queryuser(Integer page,Integer rows)

    {
        return seStuservice.queryuser(page,rows);
    }


    @RequestMapping("queryfang")
    @ResponseBody
    public HashMap<String,Object> queryfang(Integer page,Integer rows)

    {
        return seStuservice.queryfang(page,rows);
    }


}
