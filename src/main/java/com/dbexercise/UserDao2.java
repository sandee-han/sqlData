package com.dbexercise;

import com.dbexercise.domain.User;

import java.sql.*;
import java.util.Map;

public abstract class UserDao2 {

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

    public void delete(String id) {

    }


    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement("INSERT INTO users(id, name, password) VALUES(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        int status = ps.executeUpdate();    // ctrl + enter
        //  executeUpdate 반환값 : row cnt

        System.out.println(status);
        ps.close();
        c.close();
        System.out.println("DB Insert 완료");
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement("SELECT id, name, password FROM users WHERE id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        NUserDao nUserDao = new NUserDao();
        User user = nUserDao.get("2");
        nUserDao.add(new User("6", "son", "1234qwer"));
        System.out.println(user.getName());
    }
}
