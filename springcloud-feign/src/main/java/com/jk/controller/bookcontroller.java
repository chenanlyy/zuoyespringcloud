package com.jk.controller;

import com.jk.model.*;
import com.jk.service.booksercice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("b")
@Api(value = "aaa")
public class bookcontroller {
    @Autowired
    private booksercice bksercice;


    @Autowired
    private MongoTemplate mongoTemplate;
    @RequestMapping("query")
    @ApiOperation(value = "测试传参")
    public void query() {
        bksercice.queryCar("少时诵诗书所所所所");
    }

    @GetMapping("log")
    //登录方法
    public String log(String username, String pwd, String tai, HttpSession sessionession) {

        UserMdel user = bksercice.log(username, pwd);



        if (user != null) {

            sessionession.setAttribute("user", user);

            return "登录成功";
        } else {

            return "账号或密码错误";
        }


    }
//查询房子数据
    @GetMapping("queryzhu")
    public List<HomestayMdel> queryzhu() {
        return bksercice.queryzhu();
    }

    @GetMapping("queryge")
    public UserMdel queryge(String id) {


        return bksercice.queryge(id);
    }

    @PostMapping("adduser")
    public void add(UserMdel user) {


        bksercice.add(user);
    }
    @PostMapping("addhome")
    public  void addhome( HomestayMdel homepojo,HttpSession HttpSession) {
        UserMdel user= (UserMdel) HttpSession.getAttribute("user");
        bksercice.addhome(homepojo);
    }
    @GetMapping("queryimg")
    public List<ImgModel> queryimg(String id) {
        return bksercice.queryimg(id);
    }
    @GetMapping("queryxiao")
    public List<xiaoModel> queryxiao(String id) {
       id="1";

        return bksercice.queryxiao(id);
    }

    //查询评论数据
    @PostMapping("queryCommentw")
    public List<Comment> queryCommentw(Integer kechengid, String pid) {
        String commentid = (kechengid.toString());
        List<Comment> list = bksercice.queryCommentById(commentid, pid);

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                List<Comment> list2 = queryCommentw(kechengid, list.get(i).getId());
                list.get(i).setReply(list2);
            }
        }
        return list;
    }


    //新增评论
    @PostMapping("addComment")
    public void addComment(Comment pl, Integer kechengid, HttpSession HttpSession) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String commentid = (kechengid.toString());
        String pid = "0";

        // 屏蔽骂人的话
        String str = pl.getCommentcontent();
        String words[] = {"畜生", "傻逼", "垃圾", "废物", "妈的", "草泥马",
                "尼玛", "你妈", "弱智", "脑残", "傻狗", "煞笔", "烂",
                "滚", "有病", "脑瘫", "狗贼", "杂种", "靠"};
        for (int i = 0; i < words.length; i++) {
            //计算需要的*的个数
            String s = "";
            for (int j = 0; j < words[i].length(); j++) {

                s = "*";
            }
            str = str.replaceAll(words[i], s);    //  使用replace把脏话替换
        }
        pl.setCommentcontent(str);


        if (pl.getCommentpid() != null && !"".equals(pl.getCommentpid())) {    //  被回复的评论的id有为评论id 无默认为0
            pid = pl.getCommentpid();
        }
        if (pl.getReplyname() != null && !"".equals(pl.getReplyname())) {  //  没被回复人名字说明是评论不新增 有说明是回复评论的人
            pl.setReplyname(pl.getReplyname());
        }

        pl.setCommentcontent(pl.getCommentcontent());   //  评论内容
      /*  User user = (User) session.getAttribute("user");
        String userName = user.getUserName();
*/

              UserMdel user= (UserMdel) HttpSession.getAttribute("user");
        pl.setCommentid(commentid);      //     评论教程
        pl.setCommentname(user.getUsername());    //  评论人
        pl.setCommentpid(pid); //  被回复的评论的id 有为评论id无为0
        pl.setCommentdate(sdf.format(new Date()));  //  评论日期
        pl.setPraise(0);    //  点赞数默认为0
        mongoTemplate.save(pl);
        Praise praise = new Praise();

        mongoTemplate.save(praise);

    }
    @PostMapping("toPraise")
    public Integer toPraise(String id, HttpServletRequest request) {
        UserMdel user = (UserMdel) request.getSession().getAttribute("user");
        Integer flag = 1;

        Integer userid = user.getId(); //  当前登陆用户id
        //  查询是否点过这条评论的赞
        List<Praise> list = bksercice.queryPraise(userid, id);


        Query query = new Query();
        if (id != "") {
            query.addCriteria(Criteria.where("id").is(id));


        }
        //  取出这条评论点赞数
        List<Comment> plList = mongoTemplate.find(query, Comment.class);
        Comment pl = plList.get(0);
        Integer dianzan = pl.getPraise();


        //  如果用户点过赞那么点赞数-1  并且删除点赞记录
        if (list != null && list.size() > 0) {
            bksercice.delPraise(list.get(0).getId());
            pl.setPraise(dianzan - 1);
            mongoTemplate.save(pl);
            flag = 0;
        } else {  //  如果用户没有点过赞那么点赞数+1  并且增加点赞记录
            bksercice.addPraise(userid, id);
            pl.setPraise(dianzan + 1);
            mongoTemplate.save(pl);

        }
        return flag;
    }

    @PostMapping("delComment")
    public void deleteComment(String id, Integer kechengid) {
        Comment pl = new Comment();
        String commentid = (kechengid.toString());
        pl.setId(id);
        pl.setCommentid(commentid);
        mongoTemplate.remove(pl);
    }





}