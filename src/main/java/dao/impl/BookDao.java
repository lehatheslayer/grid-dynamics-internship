package dao.impl;

import dao.Dao;
import entity.Book;
import exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends Dao {
    private static BookDao instance;

    private BookDao() {
    }

    public static BookDao getInstance() {
        if (instance == null) {
            instance = new BookDao();
        }

        return instance;
    }

    public Book getById(int id) throws DatabaseException {
        logger.info(String.format("Request to get element of %s by id: %d", BookDao.class, id));
        final String sqlRequest = String.format("select * from book where id = %d", id);

        try {
            final PreparedStatement statement = this.getPreparedStatement(sqlRequest);
            final ResultSet result = statement.executeQuery();

            if (result.next()) {
                logger.info(String.format("The sql request '%s' completed successfully", sqlRequest));
                final Book book = new Book(
                        result.getString("name"),
                        result.getString("description"),
                        result.getString("author"),
                        result.getDouble("cost")
                );
                book.setId(result.getInt("id"));

                closePreparedStatement(statement);
                return book;
            }
        } catch (SQLException e) {
            logger.error(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
            throw new DatabaseException(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
        }

        return null;
    }

    public List<Book> getAll() throws DatabaseException {
        logger.info(String.format("Request to get all elements of %s", BookDao.class));
        final String sqlRequest = "SELECT * FROM BOOK";

        try {
            final PreparedStatement statement = this.getPreparedStatement(sqlRequest);
            final List<Book> result = new ArrayList<>();
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                final Book book = new Book(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("author"),
                        resultSet.getDouble("cost")
                );
                book.setId(resultSet.getInt("id"));

                result.add(book);
            }

            logger.info(String.format("The sql request '%s' completed successfully", sqlRequest));
            closePreparedStatement(statement);
            return result;
        } catch (SQLException e) {
            logger.error(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
            throw new DatabaseException(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
        }
    }

    public int save(Book item) throws DatabaseException {
        logger.info(String.format("Request to save element of %s", BookDao.class));
        final String sqlRequest = "insert into book (name, description, author, cost) values (?, ?, ?, ?)";

        try {
            final PreparedStatement statement = this.getPreparedStatement(sqlRequest);

            statement.setString(1, item.getName());
            statement.setString(2, item.getDescription());
            statement.setString(3, item.getAuthor());
            statement.setString(4, item.getCost().toString());

            statement.executeUpdate();
            logger.info(String.format("The sql request '%s' completed successfully", sqlRequest));

            try (ResultSet rs = statement.getGeneratedKeys()) {
                rs.next();
                int id = rs.getInt(1);
                closePreparedStatement(statement);

                return id;
            }
        } catch (SQLException e) {
            logger.error(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
            throw new DatabaseException(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
        }
    }

    /**
     * request to update entity's parameters by id
     *
     * @param id     - id of entity that needs to update
     * @param params - format: params[0] - name, params[1] - description, params[2] - author, params[3] - cost
     */
    public void update(int id, String[] params) throws DatabaseException {
        logger.info(String.format("Request to update element of %s", BookDao.class));
        final String sqlRequest =
                String.format("update book set name=?, description=?, author=?, cost=? where id = %d", id);

        try {
            final PreparedStatement statement = this.getPreparedStatement(sqlRequest);

            statement.setString(1, params[0]);
            statement.setString(2, params[1]);
            statement.setString(3, params[2]);
            statement.setString(4, params[3]);

            statement.executeUpdate();
            logger.info(String.format("The sql request '%s' completed successfully", sqlRequest));
            closePreparedStatement(statement);
        } catch (SQLException e) {
            logger.error(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
            throw new DatabaseException(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
        }
    }

    public void delete(int id) throws DatabaseException {
        logger.info(String.format("Request to delete element of %s by id %d", BookDao.class, id));
        final String sqlRequest = "delete from book where id = " + id;

        try {
            final PreparedStatement statement = this.getPreparedStatement(sqlRequest);

            statement.executeUpdate();
            logger.info(String.format("The sql request '%s' completed successfully", sqlRequest));
            closePreparedStatement(statement);
        } catch (SQLException e) {
            logger.error(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
            throw new DatabaseException(String.format("Cannot to complete the sql request '%s': %s", sqlRequest, e));
        }
    }
}
