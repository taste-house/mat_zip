package com.matzip.matzipback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MatzipbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatzipbackApplication.class, args);
    }

}
