package service.resthandlers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Book;
import exceptions.DatabaseException;
import jakarta.servlet.http.HttpServletRequest;
import service.BookDaoService;
import service.resthandlers.RestApiHandler;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

public class RestApiPostRequestHandler implements RestApiHandler {
    private static RestApiPostRequestHandler instance = null;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BookDaoService bookDaoService = BookDaoService.getInstance();

    private RestApiPostRequestHandler() { }

    public static RestApiPostRequestHandler getInstance() {
        if (instance == null) {
            instance = new RestApiPostRequestHandler();
        }

        return instance;
    }

    @Override
    public Optional<String> handleRestRequest(String request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int handleRestRequest(String request, HttpServletRequest req) throws IOException, DatabaseException {
        int generatedId = -1;

        if (request.matches("^/books/$")) {
            String bodyParams = req.getReader().lines().collect(Collectors.joining());
            System.out.println(bodyParams);
            Book book = objectMapper.readValue(bodyParams, Book.class);

            generatedId = bookDaoService.save(book);
        }

        return generatedId;
    }
}
