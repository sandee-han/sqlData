package com.dbexercise;

import com.dbexercise.connectionmaker.ConnectionMaker;
import com.dbexercise.connectionmaker.DConnectionMaker;
import com.dbexercise.dao.UserDao;
import com.dbexercise.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoConfig.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    @DisplayName("INSERT, SELECT TEST")
    void addAndSelect() throws SQLException, ClassNotFoundException {
        UserDao userDao = context.getBean("userDao", UserDao.class);

        String id = "13";
        User user = new User(id, "nimo", "fish");
        userDao.add(user);

        User selectUser = userDao.getById(id);
        Assertions.assertEquals("nimo", selectUser.getName());
        Assertions.assertEquals("fish", selectUser.getPassword());
    }

}
