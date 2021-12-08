package com.eventidge.eventidgeapi;

import com.eventidge.eventidgeapi.domain.repository.CustomJpaRepository;
import com.eventidge.eventidgeapi.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class EventidgeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventidgeApiApplication.class, args);
    }

}
