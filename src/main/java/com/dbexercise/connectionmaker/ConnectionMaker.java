package com.dbexercise.connectionmaker;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
    // 이 메서드를 구현하면, 어떤 형태로든 커넥션을 제공해준다는 보장을 할 수 있다.
}
