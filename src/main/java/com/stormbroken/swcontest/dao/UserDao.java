package com.stormbroken.swcontest.dao;

import com.stormbroken.swcontest.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserDao {
    @Select("select * from user where name=#{name}")
    User findByName(String name);  //看是否存在这个用户名
    @Insert("insert into user(id,name,category,password,createtime,content,friendIDs,ban,likes,history) " +
            "values(#{id},#{name},#{category},#{password},#{createtime},#{content},#{friendIDs},#{ban},#{likes},#{history})")
    void addUser(User account);   //增加一个用户
}
