package com.stormbroken.swcontest.dao;

import com.stormbroken.swcontest.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogDao {
    @Insert("insert into log(uid,request,response)" +
            "values(#{uid},#{request},#{response})")
    void insert(Log log);
}
