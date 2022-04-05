package service.db.connection;

import lombok.Getter;

@Getter
public abstract class DbPropertiesReader {
    protected static final String PROPERTIES = "/application.properties";

    protected String url;
    protected String username;
    protected String password;

    public abstract void readProperty();
}
