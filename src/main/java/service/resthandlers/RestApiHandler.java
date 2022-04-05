package service.resthandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import exceptions.DatabaseException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Optional;

public interface RestApiHandler {
    Optional<String> handleRestRequest(String request) throws DatabaseException, JsonProcessingException;

    int handleRestRequest(String request, HttpServletRequest req) throws IOException, DatabaseException;
}
