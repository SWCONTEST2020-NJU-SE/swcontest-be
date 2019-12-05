package com.stormbroken.swcontest;

import com.stormbroken.swcontest.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SwcontestApplication {
    public static Map<String, User> tokenMap = new HashMap();

    /**
     * token用来避免httpsession以完成对于用户权限的认证和存储
     * @param token
     * @return
     */
    public static User findAccountByToken(String token){
        for(String string:tokenMap.keySet()){
            if(string.equals(token)){
                return tokenMap.get(string);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SpringApplication.run(SwcontestApplication.class, args);
    }

}
