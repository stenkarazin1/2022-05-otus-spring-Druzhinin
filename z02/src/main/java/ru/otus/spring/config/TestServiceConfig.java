package ru.otus.spring.config;

import ru.otus.spring.logic.TestBox;
import ru.otus.spring.service.TestService;
import ru.otus.spring.service.TestServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestServiceConfig {

    @Bean("testService")
    public TestService testService (TestBox box) {
        return new TestServiceImpl(box);
    }

}
