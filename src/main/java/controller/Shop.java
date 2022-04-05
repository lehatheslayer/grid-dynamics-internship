package controller;

import exceptions.DatabaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import service.BookDaoService;

import java.io.IOException;

@WebServlet(name = "shop", value = "/")
public class Shop extends HttpServlet {
    private static final BookDaoService bookDaoService = BookDaoService.getInstance();
    private static final Logger logger = Logger.getLogger(Shop.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("Request to get the list of books");
            req.setAttribute("books", bookDaoService.getAll());
            logger.info("The list of books were got successfully");
        } catch (DatabaseException e) {
            logger.error(e);
        }

        req.getRequestDispatcher("shop.jsp").forward(req, resp);
    }
}
