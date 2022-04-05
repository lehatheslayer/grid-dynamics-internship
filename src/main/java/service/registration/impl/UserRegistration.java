package service.registration.impl;

import dao.impl.UserDao;
import entity.User;
import exceptions.DatabaseException;
import exceptions.UserAlreadyExistsException;
import org.apache.commons.codec.digest.DigestUtils;
import service.registration.Registration;

public class UserRegistration implements Registration {
    private static UserRegistration instance = null;

    private final UserDao userDao = UserDao.getInstance();

    private UserRegistration() { }

    public static UserRegistration getInstance() {
        if (instance == null) {
            instance = new UserRegistration();
        }

        return instance;
    }

    @Override
    public User register(String username, String password, String email, int roleId)
            throws DatabaseException, UserAlreadyExistsException {
        User userFromDb = userDao.getByParams(username);
        if (userFromDb != null) {
            throw new UserAlreadyExistsException("User + '" + username + "' already exists");
        }

        userFromDb = new User(username, DigestUtils.md5Hex(password), email, roleId);
        userDao.save(userFromDb);

        return userFromDb;
    }
}
