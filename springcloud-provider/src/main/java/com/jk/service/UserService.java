package com.jk.service;

import com.jk.model.HomestayMdel;
import com.jk.model.ImgModel;
import com.jk.model.UserMdel;
import com.jk.model.xiaoModel;

import java.util.List;

public interface UserService  {
    UserMdel log(String username, String pwd);

    List<HomestayMdel> queryzhu();

    UserMdel queryge(String id);

    void add(UserMdel user);

    HomestayMdel queryzhu2(String id);

    List<ImgModel> queryimg(String id);

    List<xiaoModel> queryxiao(String id);
}
