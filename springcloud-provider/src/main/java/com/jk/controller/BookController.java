package com.jk.controller;

import com.jk.model.*;
import com.jk.service.Bookserver;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class BookController implements Bookserver {
    @Autowired
private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;
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

    @Override
    public List<xiaoModel> queryxiao(String id) {
        return userService.queryxiao(id);
    }

    @Override
    public List<Comment> queryCommentById(String commentid, String pid) {

        Query query = new Query();

        if (commentid!=null && !"".equals(commentid)) {
            query.addCriteria(Criteria.where("commentid").is(commentid));
        }
        if (pid!=null && !"".equals(pid)) {
            query.addCriteria(Criteria.where("commentpid").is(pid));
        }
        long zongshu = mongoTemplate.count(query, Comment.class);

        List<Comment> list = mongoTemplate.find(query, Comment.class);
        return list;
    }

    @Override
    public List<Praise> queryPraise(Integer userid, String id) {
        String usid = userid.toString();
        Query query = new Query();
        if (usid!=null && !"".equals(usid)) {
            query.addCriteria(Criteria.where("usid").is(usid));
        }
        if (id!=null && !"".equals(id)) {
            query.addCriteria(Criteria.where("coid").is(id));
        }

        List<Praise> list = mongoTemplate.find(query, Praise.class);
        return list;
    }

    @Override
    public void delPraise(String id) {
        Praise dz = new Praise();
        dz.setId(id);
        mongoTemplate.remove(dz);
    }

    @Override
    public void addPraise(Integer userid, String id) {
        Praise dz = new Praise();
        dz.setUsid(userid.toString());
        dz.setCoid(id);
        mongoTemplate.save(dz);
    }


    public static void main(String[] args) {

        System.out.println( Math.round(-1.5));
    }
}
