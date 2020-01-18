package com.jk.service;

import com.jk.mapper.houmapper;
import com.jk.model.HomestayMdel;
import com.jk.model.TreeBean;
import com.jk.model.UserMdel;
import com.jk.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class houservice implements houservicee {
    @Autowired
    private houmapper dao;
    @Override
    public UserModel queryUserName(String username) {
        return dao.queryUserName(username);
    }

    @Override
    public List<TreeBean> queryTree(HttpSession session) {



        UserModel u = (UserModel) session.getAttribute("user");




        Integer userid = u.getId();


            int pid = 0;

            List<TreeBean>      list = queryNodes(pid,userid);








        return list;
    }

    @Override
    public HashMap<String, Object> queryuser(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        int start=((page-1)*rows);
        int count=dao.querycount();
     List<UserMdel> list=dao.queryuser(start,rows);
        map.put("total",count);
        map.put("rows",list);
        return map;
    }

    @Override
    public HashMap<String, Object> queryfang(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        int start=((page-1)*rows);
        int count=dao.queryfangcount();
        List<HomestayMdel> list=dao.queryfang(start,rows);
        map.put("total",count);
        map.put("rows",list);
        return map;
    }

    private List<TreeBean> queryNodes(int pid, Integer userid) {
        List<TreeBean> list = dao.queryTree(pid,userid);
        for (TreeBean treeBean : list) {
            Integer id = treeBean.getId();
            //查询对应的子节点
            List<TreeBean> nodes = queryNodes(id,userid);//递归：自己调自己
            treeBean.setChildren(nodes);
        }
        return list;
    }
}
