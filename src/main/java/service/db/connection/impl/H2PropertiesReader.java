package service.db.connection.impl;

import service.db.connection.DbPropertiesReader;

import java.io.IOException;
import java.util.Properties;

public class H2PropertiesReader extends DbPropertiesReader {
    @Override
    public void readProperty() {
        Properties properties = new Properties();

        try {
            properties.load(this.getClass().getResourceAsStream(PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.url = properties.getProperty("h2.datasource.url");
        this.username = properties.getProperty("h2.datasource.username");
        this.password = properties.getProperty("h2.datasource.password");
    }
}
