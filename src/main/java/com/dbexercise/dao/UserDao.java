package com.dbexercise.dao;

import com.dbexercise.connectionmaker.AwsConnectionMaker;
import com.dbexercise.connectionmaker.ConnectionMaker;
import com.dbexercise.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }


    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("INSERT INTO users(id, name, password) VALUES(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();    // ctrl + enter
        //  executeUpdate 반환값 : row cnt

        ps.close();
        c.close();
        System.out.println("DB Insert 완료");
    }

    public User getById(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM users WHERE id = ?");

        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public void delete() throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement("DELETE FROM USERS");
        ps.executeUpdate();
        ps.close();
        c.close();

    }
    public void deleteById(String id) throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement("DELETE FROM USERS WHERE id = ?");
        ps.setString(1, id);
        ps.executeUpdate();
        ps.close();
        c.close();

    }
}
