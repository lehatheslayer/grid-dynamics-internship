package controller;

import exceptions.DatabaseException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import service.BookDaoService;

import java.io.IOException;

@WebServlet(name="delete/book", value = "/delete/book")
public class DeleteBook extends HttpServlet {
    private static final BookDaoService bookDaoService = BookDaoService.getInstance();
    private static final Logger logger = Logger.getLogger(DeleteBook.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final int id = Integer.parseInt(req.getParameter("id"));

        try {
            logger.info("Request to delete book with id " + id);
            bookDaoService.delete(id);
            logger.info("The book with id " + id + " was deleted successfully");
        } catch (DatabaseException e) {
            logger.error(e);
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
