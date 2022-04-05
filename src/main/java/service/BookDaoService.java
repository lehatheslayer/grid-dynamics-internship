package service;

import dao.impl.BookDao;
import entity.Book;
import exceptions.DatabaseException;

import java.util.List;

public class BookDaoService {
    private static final BookDao bookDao = BookDao.getInstance();

    private static BookDaoService instance = null;

    private BookDaoService() { }

    public static BookDaoService getInstance() {
        if (instance == null) {
            instance = new BookDaoService();
        }

        return instance;
    }

    public List<Book> getAll() throws DatabaseException {
        return bookDao.getAll();
    }

    public int save(Book book) throws DatabaseException {
        return bookDao.save(book);
    }

    public void delete(int id) throws DatabaseException {
        bookDao.delete(id);
    }

    public Book getById(int id) throws DatabaseException {
        return bookDao.getById(id);
    }
}
