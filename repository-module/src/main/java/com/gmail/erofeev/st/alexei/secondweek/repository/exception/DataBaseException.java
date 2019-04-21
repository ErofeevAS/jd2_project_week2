package com.gmail.erofeev.st.alexei.secondweek.repository.exception;

import java.sql.SQLException;

public class DataBaseException extends RuntimeException {
    public DataBaseException(String message, SQLException e) {
        super(message, e);
    }

    public DataBaseException(String message) {
        super(message);
    }
}
