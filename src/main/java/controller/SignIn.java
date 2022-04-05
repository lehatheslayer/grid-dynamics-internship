package controller;

import entity.User;
import exceptions.DatabaseException;
import exceptions.UserNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import service.authentication.Authentication;
import service.authentication.impl.UserAuthentication;

import java.io.IOException;

@WebServlet(name = "signIn", value = "/signIn")
public class SignIn extends HttpServlet {
    private final static Authentication userAuthenticationService = UserAuthentication.getInstance();
    private final static Logger logger = Logger.getLogger(SignIn.class);
    private final static int MAX_INACTIVE_INTERVAL = 900;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User userFromDb = userAuthenticationService.auth(
                    req.getParameter("name"),
                    req.getParameter("password")
            );

            HttpSession session = req.getSession(true);
            session.setAttribute("loginUser", userFromDb);
            session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);

            resp.sendRedirect(req.getContextPath() + "/");
        } catch (DatabaseException e) {
            logger.error(e);
        } catch (UserNotFoundException e) {
            logger.error(e);
            req.setAttribute("message", e);
            getServletContext().getRequestDispatcher("/failed.jsp").forward(req, resp);
        }
    }
}
