package com.jk.mapper;

import com.jk.model.HomestayMdel;
import com.jk.model.ImgModel;
import com.jk.model.UserMdel;
import com.jk.model.xiaoModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface bookmapper {
    @Select("SELECT * FROM t_user  u where ( u.username = #{username} OR u.phone=#{username} ) AND u.pwd=#{pwd} ")
    UserMdel log(@Param("username") String username,@Param("pwd") String pwd);

    List<HomestayMdel> queryzhu();
    @Select("SELECT * FROM t_user where id= #{id}")
    UserMdel queryge(@Param("id") String id);

    void add(UserMdel user);

    HomestayMdel queryzhu2(@Param("id") String id);
    @Select("SELECT * FROM t_img where tid =#{id} ")
    List<ImgModel> queryimg(@Param("id") String id);

    List<xiaoModel> queryxiao(@Param("id") String id);
}
