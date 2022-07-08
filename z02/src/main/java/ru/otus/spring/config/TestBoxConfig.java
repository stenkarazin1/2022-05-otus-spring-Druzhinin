package ru.otus.spring.config;

import ru.otus.spring.dao.TestBoxDao;
import ru.otus.spring.logic.TestBox;
import ru.otus.spring.logic.TestBoxImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class TestBoxConfig {

    @Bean("testBox")
    public TestBox testBox ( @Value("${configfile}") String fileName, TestBoxDao dao) {
        return new TestBoxImpl( fileName, dao);
    }

}
