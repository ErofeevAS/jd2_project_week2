package com.gmail.erofeev.st.alexei.secondweek.controller.aspect;

import org.springframework.stereotype.Component;

@Component
public class TimeCounter {
    private Long startTime;
    private Long stopTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public Long stop() {
        stopTime = System.currentTimeMillis();
        return stopTime - startTime;
    }
}
