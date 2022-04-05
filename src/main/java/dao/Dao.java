package dao;

import exceptions.DatabaseException;
import org.apache.log4j.Logger;
import service.db.connection.DbConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Dao {
    protected static final Logger logger = Logger.getLogger(Dao.class);

    protected PreparedStatement getPreparedStatement(String sql) throws DatabaseException {
        logger.info("Trying to create prepared statement for sql request '" + sql + "'");
        final Connection connection = DbConnectionPool.getInstance().getConnection("h2");

            try {
                logger.info("The prepared statement for sql request '" + sql + "' created successfully");
                return connection.prepareStatement(sql);
            } catch (SQLException e) {
                logger.error(e);
                throw new DatabaseException(e);
            }
    }

    protected void closePreparedStatement(PreparedStatement statement) throws DatabaseException {
        logger.info("Trying to close prepared statement " + statement);

        try {
            DbConnectionPool.getInstance().closeConnection("h2", statement.getConnection());
            statement.close();
            logger.info("The prepared statement " + statement + " closed successfully");
        } catch (SQLException e) {
            logger.error("Cannot to close the prepared statement " + statement + ": " + e);
            throw new DatabaseException(e);
        }
    }
}
