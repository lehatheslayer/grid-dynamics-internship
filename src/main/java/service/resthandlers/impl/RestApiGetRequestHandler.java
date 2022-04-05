package service.resthandlers.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Book;
import exceptions.DatabaseException;
import jakarta.servlet.http.HttpServletRequest;
import service.BookDaoService;
import service.resthandlers.RestApiHandler;

import java.util.List;
import java.util.Optional;

public class RestApiGetRequestHandler implements RestApiHandler {
    private static RestApiGetRequestHandler instance = null;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BookDaoService bookDaoService = BookDaoService.getInstance();

    private RestApiGetRequestHandler() { }

    public static RestApiGetRequestHandler getInstance() {
        if (instance == null) {
            instance = new RestApiGetRequestHandler();
        }

        return instance;
    }

    @Override
    public Optional<String> handleRestRequest(String request) throws DatabaseException, JsonProcessingException {
        if (request.matches("^/books/$")) {
            final List<Book> books = bookDaoService.getAll();

            return Optional.ofNullable(objectMapper.writeValueAsString(books));
        } else if (request.matches("^/books/\\d+$")) {
            final Book book = bookDaoService.getById(parseId(request));

            return Optional.ofNullable(objectMapper.writeValueAsString(book));
        }

        return Optional.empty();
    }

    @Override
    public int handleRestRequest(String request, HttpServletRequest req) {
        throw new UnsupportedOperationException();
    }


    private int parseId(String request) {
        return Integer.parseInt(request.split("/")[2]);
    }
}
