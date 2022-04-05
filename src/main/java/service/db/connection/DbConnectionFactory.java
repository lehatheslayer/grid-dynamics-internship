package service.db.connection;

public interface DbConnectionFactory {
    DbConnectionManager createDbConnection();
}
