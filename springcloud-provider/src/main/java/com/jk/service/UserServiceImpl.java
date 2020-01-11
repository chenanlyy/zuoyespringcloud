package com.jk.service;

import com.jk.mapper.bookmapper;
import com.jk.model.HomestayMdel;
import com.jk.model.ImgModel;
import com.jk.model.UserMdel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private bookmapper dao;
    @Override
    public UserMdel log(String username, String pwd) {

        return dao.log(username,pwd);
    }

    @Override
    public List<HomestayMdel> queryzhu() {



        return dao.queryzhu();
    }

    @Override
    public UserMdel queryge(String id) {
        return dao.queryge(id);
    }

    @Override
    public void add(UserMdel user) {
        System.out.println(user.getFamilyname());
        dao.add(user);
    }

    @Override
    public HomestayMdel queryzhu2(String id) {

        return dao.queryzhu2(id);
    }

    @Override
    public List<ImgModel> queryimg(String id) {
        return dao.queryimg(id);
    }
}
