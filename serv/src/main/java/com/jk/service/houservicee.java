package com.jk.service;

import com.jk.model.TreeBean;
import com.jk.model.UserModel;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public interface houservicee {
    UserModel queryUserName(String username);

    List<TreeBean> queryTree(HttpSession session);

    HashMap<String, Object> queryuser(Integer page, Integer rows);

    HashMap<String, Object> queryfang(Integer page, Integer rows);
}
