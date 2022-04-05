package service.db.connection;

import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;

@Getter
public abstract class DbConnectionManager {
    protected DbPropertiesReader propertiesReader;

    protected DbConnectionManager() { }

    public abstract Connection openConnection() throws ClassNotFoundException, SQLException;

    public abstract boolean closeConnection();
}
