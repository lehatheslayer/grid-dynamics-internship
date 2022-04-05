package controller.rest;

import exceptions.DatabaseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.resthandlers.RestApiHandler;
import service.resthandlers.impl.RestApiGetRequestHandler;
import service.resthandlers.impl.RestApiPostRequestHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(name = "rest", urlPatterns = "/rest/*")
public class RestServlet extends HttpServlet {
    private final static RestApiHandler restApiGetRequestHandler = RestApiGetRequestHandler.getInstance();
    private final static RestApiHandler restApiPostRequestHandler = RestApiPostRequestHandler.getInstance();

    private final static int HTTP_OK_STATUS = 200;
    private final static int HTTP_CREATED_STATUS = 201;
    private final static int HTTP_BAD_REQUEST_STATUS = 400;
    private final static int HTTP_NOT_FOUND_STATUS = 404;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        try {
            final Optional<String> userResponse = restApiGetRequestHandler.handleRestRequest(path);
            if (userResponse.isPresent()) {
                resp.setContentType("application/json; charset=UTF-8");
                resp.setStatus(HTTP_OK_STATUS);
                final PrintWriter out = resp.getWriter();
                out.write(userResponse.get());
            }


        } catch (DatabaseException e) {
            resp.setStatus(HTTP_NOT_FOUND_STATUS);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        try {
            final int generatedId = restApiPostRequestHandler.handleRestRequest(path, req);
            resp.setContentType("application/json; charset=UTF-8");
            resp.setStatus(HTTP_CREATED_STATUS);
            final PrintWriter out = resp.getWriter();
            out.write(String.format("Created book id: %d", generatedId));
        } catch (DatabaseException e) {
            resp.setStatus(HTTP_BAD_REQUEST_STATUS);
        }
    }
}
