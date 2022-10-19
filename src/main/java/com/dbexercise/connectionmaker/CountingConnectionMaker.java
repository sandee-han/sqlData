package com.dbexercise.connectionmaker;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker{
    int counter = 0;
    private ConnectionMaker realConnnectionMaker;

    public CountingConnectionMaker(ConnectionMaker realConnnectionMaker) {
        this.realConnnectionMaker = realConnnectionMaker;
    }

    @Override
    public Connection makeConnection() throws SQLException, ClassNotFoundException {
        this.counter++;
        return realConnnectionMaker.makeConnection();
    }

    public int getCounter() {
        return this.counter;
    }
}
