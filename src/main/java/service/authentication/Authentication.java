package service.authentication;

import entity.User;
import exceptions.DatabaseException;
import exceptions.UserNotFoundException;

public interface Authentication {
    User auth(String username, String password) throws DatabaseException, UserNotFoundException;
}
