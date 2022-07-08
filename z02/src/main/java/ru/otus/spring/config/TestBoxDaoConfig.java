package ru.otus.spring.config;

import ru.otus.spring.dao.TestBoxDao;
import ru.otus.spring.dao.TestBoxDaoSimple;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBoxDaoConfig {

    @Bean("testBoxDao")
    public TestBoxDao testBoxDao () {
        return new TestBoxDaoSimple();
    }

}
