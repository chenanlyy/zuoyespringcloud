package com.jk.controller;

import com.jk.model.HomestayMdel;
import com.jk.model.UserMdel;
import com.jk.service.booksercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("p")
public class pagecontroller {
    @Autowired
    private booksercice bksercice;
    @RequestMapping("logjsp")
    public String  logjsp(HttpSession session) {

        return "login";
    }
    @RequestMapping("index")
    public String  index(HttpSession sessionession) {
      UserMdel us= (UserMdel) sessionession.getAttribute("user");
  if (us==null){
      UserMdel userMdel = new UserMdel();
      userMdel.setId(-1);

      userMdel.setUsername("未登录");
      sessionession.setAttribute("user",userMdel);
  }

        return "index";
    }
    @RequestMapping("ge")
    public String  ge(String id,HttpSession sessionession) {
        UserMdel queryge = bksercice.queryge(id);
        sessionession.setAttribute("us",queryge);
        return "personal";
    }

    @RequestMapping("fang")
    public String  fang() {
        return "fang";
    }
    @RequestMapping("xiang")
    public String  xiang(String id,HttpSession sessionession) {
        HomestayMdel queryge = bksercice.queryzhu2(id);
        sessionession.setAttribute("us2",queryge);
        return "zhu";
    }
    @RequestMapping("ditu")
    public String  ditu() {
        return "ditu";
    }
    @RequestMapping("popupsignin")
    public String  popupsignin() {
        return "popupsignin";
    }
}
