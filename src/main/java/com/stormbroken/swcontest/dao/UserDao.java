package com.stormbroken.swcontest.dao;

import com.stormbroken.swcontest.entity.Edit;
import com.stormbroken.swcontest.entity.User;
import com.stormbroken.swcontest.form.RegisterForm;
import com.stormbroken.swcontest.vo.ChangePasswordVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserDao {
    @Select("select * from user where name=#{name}")
    User findByName(String name);  //看是否存在这个用户名，上面就是方法
    @Select("select * from account where name=#{name} and password=#{password}")
    User findByNameAndPassword(String username,String password);   //根据用户名和密码查询
    @Insert("insert into user(name,password,createtime,content,url) " +
            "values(#{name},#{password},#{createtime},#{content},#{url})")
    void addUser(RegisterForm registerForm);   //增加一个用户
    @Update("update user set name=#{name},content = #{content},url = #{url} where name=#{username}")
    void updateUser(Edit edit);
    @Update("update user set password=#{password} where name=#{name}")
    void changePassword(ChangePasswordVO changePasswordVO);   //修改密码

}
