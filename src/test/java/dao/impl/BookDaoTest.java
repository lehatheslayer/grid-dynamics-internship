package dao.impl;

import entity.Book;
import exceptions.DatabaseException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookDaoTest {
    private static final BookDao DATA_ACCESS_OBJECT = BookDao.getInstance();

    @Test
    void getById() throws DatabaseException {
        final Book result = DATA_ACCESS_OBJECT.getById(1);

        assertNotNull(result);
        assertEquals(
                new Book("war&peace", "1812", "tolstoy", 1900.0),
                result
        );
    }

    @Test
    void getAll() throws DatabaseException {
        final List<Book> result = DATA_ACCESS_OBJECT.getAll();

        for (Book book : result) {
            System.out.println(book.toString());
        }
    }

    @Test
    void save() throws DatabaseException {
        DATA_ACCESS_OBJECT.save(new Book("my", "name", "name", 155.0));
    }
}