package controller;

import entity.Book;
import exceptions.DatabaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import service.BookDaoService;

import java.io.IOException;

@WebServlet(name = "add/book", value = "/add/book")
public class AddBook extends HttpServlet {
    private static final BookDaoService bookDaoService = BookDaoService.getInstance();
    private static final Logger logger = Logger.getLogger(AddBook.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final Book bookToAdd = new Book(
                req.getParameter("name"),
                req.getParameter("description"),
                req.getParameter("author"),
                Double.parseDouble(req.getParameter("cost"))
        );

        try {
            logger.info("Request to save " + bookToAdd + " to database");
            bookDaoService.save(bookToAdd);
            logger.info(bookToAdd + " saved to database successfully");
        } catch (DatabaseException e) {
            logger.error(e);
        }

        resp.sendRedirect(req.getContextPath() + "/addBook");
    }


}
