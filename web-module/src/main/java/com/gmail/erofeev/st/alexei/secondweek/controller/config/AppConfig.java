package com.gmail.erofeev.st.alexei.secondweek.controller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({
        "com.gmail.erofeev.st.alexei.secondweek.controller",
        "com.gmail.erofeev.st.alexei.secondweek.service",
        "com.gmail.erofeev.st.alexei.secondweek.repository"
})
@PropertySource("classpath:jdbc.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
