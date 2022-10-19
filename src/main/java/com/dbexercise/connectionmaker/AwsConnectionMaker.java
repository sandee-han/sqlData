package com.dbexercise.connectionmaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class AwsConnectionMaker {
    public Connection makeConnection() throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbPassword = env.get("DB_PASSWORD");
        System.out.println(dbPassword);
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.printf("%s %s %s\n", env.get("DB_HOST"), env.get("DB_USER"), env.get("DB_PASSWORD"));

        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bbs", "root", dbPassword);

        Class.forName("com.mysql.cj.jdbc.Driver");

        return c;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AwsConnectionMaker awsConnectionMaker = new AwsConnectionMaker();
        awsConnectionMaker.makeConnection();
    }

}
