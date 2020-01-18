package com.jk.mapper;

import com.jk.model.HomestayMdel;
import com.jk.model.TreeBean;
import com.jk.model.UserMdel;
import com.jk.model.UserModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface houmapper {
    @Select("select * from  t_user  where username= #{username} ")
    UserModel queryUserName(@Param("username") String username);

    @Select("select t.id,t.text text,t.pid,t.url from t_tree2 t \r\n" +
            "			left join t_role_power rp on t.id = rp.powerid\r\n" +
            "			left join t_user_role  ur on rp.roleid = ur.roleid\r\n" +
            "			where t.pid=#{pid} and ur.userid = #{userid}\r\n" +
            "			group by t.id")
    List<TreeBean> queryTree(@Param("pid") int pid,@Param("userid")  Integer userid);
    @Select("select count(1) from t_user")
    int querycount();
    @Select("select * from t_user limit #{s},#{e}")
    List<UserMdel> queryuser(@Param("s") int start,@Param("e") Integer rows);

   @Select("SELECT count(1) from t_zhu  z LEFT JOIN  t_fang  f  on z.landlord= f.id")
    int queryfangcount();
    @Select(" SELECT * from t_zhu  z LEFT JOIN  t_fang  f  on z.landlord= f.id limit #{s},#{e}")
    List<HomestayMdel> queryfang(@Param("s") int start,@Param("e") Integer rows);
}
