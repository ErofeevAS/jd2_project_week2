package com.gmail.erofeev.st.alexei.secondweek.repository.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataBaseProperties {

    @Value("${database.url}")
    private String url;
    @Value("${database.username:}")
    private String username;
    @Value("${database.password:}")
    private String password;
    @Value("${database.driver.name}")
    private String driver;
    @Value("${database.serverTimezone}")
    private String serverTimezone;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    public String getServerTimezone() {
        return serverTimezone;
    }
}