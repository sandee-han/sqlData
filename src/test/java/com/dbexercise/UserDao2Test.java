package com.dbexercise;

import com.dbexercise.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDao2Test {

    @Test
    void addAndSelect() throws SQLException, ClassNotFoundException {
        NUserDao nUserDao = new NUserDao();
        String id = "6";
        User user = new User(id, "chuu", "Loona");
        nUserDao.add(user);

        User selectUser = nUserDao.get(id);
        Assertions.assertEquals("chuu", selectUser.getName());
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao2Test userDao2Test = new UserDao2Test();
        userDao2Test.addAndSelect();
    }
}