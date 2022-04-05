package controller;

import entity.User;
import exceptions.DatabaseException;
import exceptions.UserAlreadyExistsException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import service.registration.Registration;
import service.registration.impl.UserRegistration;

import java.io.IOException;

@WebServlet(name = "signUp", value = "/signUp")
public class SignUp extends HttpServlet {
    private final static Registration userRegistrationService = UserRegistration.getInstance();
    private final static Logger logger = Logger.getLogger(SignUp.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final User user = userRegistrationService.register(
                    req.getParameter("name"),
                    req.getParameter("password"),
                    req.getParameter("email"),
                    Integer.parseInt(req.getParameter("roleId"))
            );

            logger.info("The user " + user + " was created successfully");
            req.setAttribute("message", "User with name " + user.getName() + " created successfully");
            req.getRequestDispatcher("/success.jsp").forward(req, resp);
        } catch (DatabaseException e) {
            logger.error(e);
        } catch (UserAlreadyExistsException e) {
            logger.error(e);
            req.setAttribute("message", e);
            req.getRequestDispatcher("/failed.jsp").forward(req, resp);
        }
    }
}
