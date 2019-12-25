package com.stormbroken.swcontest.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TasteDao {
    @Delete("delete from order where id=#{id} and uid=#{uid}")
    void delete(int id,int uid);
    @Insert("insert into userTaste(uid,type,addition) values(#{uid},#{type},#{addition})")
    void add(int uid ,int type,String addition);
}
