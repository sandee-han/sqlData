package com.dbexercise;


import com.dbexercise.connectionmaker.ConnectionMaker;
import com.dbexercise.connectionmaker.CountingConnectionMaker;
import com.dbexercise.connectionmaker.DConnectionMaker;
import com.dbexercise.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoConfig {
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker(){
        return new CountingConnectionMaker(realConnectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectionMaker(){
        return new DConnectionMaker();
    }

}
