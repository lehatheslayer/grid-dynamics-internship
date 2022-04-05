package service.db.connection.impl;

import service.db.connection.DbConnectionManager;
import service.db.connection.DbConnectionFactory;

public class H2ConnectionFactory implements DbConnectionFactory {
    @Override
    public DbConnectionManager createDbConnection() {
        return H2ConnectionManager.create();
    }
}
