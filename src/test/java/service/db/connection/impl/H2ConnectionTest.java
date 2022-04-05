package service.db.connection.impl;

import exceptions.DatabaseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.db.connection.DbConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


class H2ConnectionTest {
    @BeforeAll
    static void configure() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
    }

    @Test
    void openConnection() throws SQLException, DatabaseException {
        var conn = DbConnectionPool.getInstance().getConnection("h2");

        String query = "select * from test";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        resultSet.next();

        assertEquals("chair", resultSet.getString("name"));
    }

    @Test
    void closeConnection() throws DatabaseException {
        var conn = DbConnectionPool.getInstance().getConnection("h2");

        assertTrue(DbConnectionPool.getInstance().closeConnection("h2", conn));
    }
}