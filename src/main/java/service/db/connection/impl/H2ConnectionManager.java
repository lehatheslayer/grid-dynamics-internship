package service.db.connection.impl;

import service.db.connection.DbConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionManager extends DbConnectionManager {
    public static DbConnectionManager create() {
        return new H2ConnectionManager();
    }

    protected H2ConnectionManager() {
        this.propertiesReader = new H2PropertiesReader();
        this.propertiesReader.readProperty();
    }

    @Override
    public Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");

        return DriverManager.getConnection(
                propertiesReader.getUrl(),
                propertiesReader.getUsername(),
                propertiesReader.getPassword()
        );
    }

    // todo realize???
    @Override
    public boolean closeConnection() {
        return false;
    }
}
