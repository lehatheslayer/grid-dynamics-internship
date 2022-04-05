package dao.impl;

import dao.Dao;
import entity.User;
import exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao {
    private static UserDao instance;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }

        return instance;
    }

    public User getByParams(String userName) throws DatabaseException {
        logger.info(String.format("Request to get element of %s by name: '%s'", UserDao.class, userName));
        final String sqlRequest = String.format("select * from user where name = '%s'", userName);

        return getUser(userName, sqlRequest);
    }

    public User getByParams(String userName, String password) throws DatabaseException {
        logger.info(String.format("Request to get element of %s by name: '%s'", UserDao.class, userName));
        final String sqlRequest =
                String.format("select * from user where name = '%s' and password = '%s'", userName, password);

        return getUser(userName, sqlRequest);
    }

    private User getUser(String userName, String sqlRequest) throws DatabaseException {
        try {
            final PreparedStatement statement = getPreparedStatement(sqlRequest);
            final ResultSet result = statement.executeQuery();

            if (result.next()) {
                logger.info(String.format("User with name %s was found", userName));
                return new User(
                        result.getString("name"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getInt("role_id")
                );
            }

            logger.info(String.format("User with name %s wasn't found", userName));
            return null;
        } catch (SQLException e) {
            logger.error(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
            throw new DatabaseException(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
        }
    }

    public void save(User item) throws DatabaseException {
        logger.info(String.format("Request to save element of %s", UserDao.class));
        final String sqlRequest = "insert into user (name, password, email) values (?, ?, ?)";

        try {
            final PreparedStatement statement = getPreparedStatement(sqlRequest);

            statement.setString(1, item.getName());
            statement.setString(2, item.getPassword());
            statement.setString(3, item.getEmail());

            statement.executeUpdate();
            logger.info(String.format("The sql request '%s' completed successfully", sqlRequest));
            closePreparedStatement(statement);
        } catch (SQLException e) {
            logger.error(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
            throw new DatabaseException(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
        }
    }
}
