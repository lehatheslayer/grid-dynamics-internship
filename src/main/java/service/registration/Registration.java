package service.registration;

import entity.User;
import exceptions.DatabaseException;
import exceptions.UserAlreadyExistsException;

public interface Registration {
    User register(String username, String password, String email, int roleId)
            throws DatabaseException, UserAlreadyExistsException;
}
