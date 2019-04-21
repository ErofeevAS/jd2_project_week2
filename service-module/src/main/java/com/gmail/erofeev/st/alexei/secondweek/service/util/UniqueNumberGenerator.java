package com.gmail.erofeev.st.alexei.secondweek.service.util;

import java.util.UUID;

public class UniqueNumberGenerator {

    public static String generate(){
        return UUID.randomUUID().toString();
    }
}
