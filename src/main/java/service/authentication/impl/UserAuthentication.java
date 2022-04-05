package service.authentication.impl;

import dao.impl.UserDao;
import entity.User;
import exceptions.DatabaseException;
import exceptions.UserNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import service.authentication.Authentication;

public class UserAuthentication implements Authentication {
    private static UserAuthentication instance;

    private UserAuthentication () { }

    public static UserAuthentication getInstance() {
        if (instance == null) {
            instance = new UserAuthentication();
        }

        return instance;
    }

    private final UserDao userDao = UserDao.getInstance();

    @Override
    public User auth(String username, String password) throws DatabaseException, UserNotFoundException {
        final User userFromDb = userDao.getByParams(username, DigestUtils.md5Hex(password));

        if (userFromDb == null) {
            throw new UserNotFoundException("Cannot login. Wrong username or password");
        }

        return userFromDb;
    }
}
