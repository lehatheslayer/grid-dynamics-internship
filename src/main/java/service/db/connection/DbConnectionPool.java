package service.db.connection;

import exceptions.DatabaseException;
import org.apache.log4j.Logger;
import service.db.connection.impl.H2ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class DbConnectionPool {
    private static final int INITIAL_POOL_SIZE = 10;
    private static final Logger logger = Logger.getLogger(DbConnectionPool.class);

    private static DbConnectionPool instance;

    private final Map<String, DbConnectionManager> managers;
    private final Map<String, List<Connection>> connectionPool;
    private final Map<String, List<Connection>> usedConnections;

    public static DbConnectionPool getInstance() {
        if (instance == null) {
            logger.info("Database connection pool was created");
            instance = new DbConnectionPool();
        }

        return instance;
    }

    private DbConnectionPool() {
        this.managers = new HashMap<>();
        addConnectionManager("h2", new H2ConnectionFactory());

        this.connectionPool = new HashMap<>();
        this.connectionPool.put("h2", new ArrayList<>());

        this.usedConnections = new HashMap<>();
        this.usedConnections.put("h2", new ArrayList<>());
    }

    private void addConnectionManager(String type, DbConnectionFactory factory) {
        managers.put(type, factory.createDbConnection());
    }

    public Connection getConnection(String type) throws DatabaseException {
        logger.info("Request to get connection to " + type + " database");

        if (connectionPool.get(type).isEmpty()) {
            if (usedConnections.get(type).size() < INITIAL_POOL_SIZE) {
                logger.info("Creating new connection to " + type + " database");

                try {
                    connectionPool.get(type).add(managers.get(type).openConnection());
                } catch (ClassNotFoundException e) {
                    logger.error("Not found h2 database driver");
                    throw new DatabaseException("Not found h2 database driver");
                } catch (SQLException e) {
                    logger.error(e);
                    throw new DatabaseException(e);
                }
            } else {
                logger.error("Maximum pool size reached, no available connections!");
                throw new DatabaseException("Maximum pool size reached, no available connections!");
            }
        }

        final Connection connection = connectionPool.get(type).remove(connectionPool.size() - 1);

        logger.info(connection + " was given for using");
        usedConnections.get(type).add(connection);

        return connection;
    }

    public boolean closeConnection(String type, Connection connection) {
        connectionPool.get(type).add(connection);

        logger.info(connection + " was returned back into pool successfully");
        return usedConnections.get(type).remove(connection);
    }
}
