package com.example.edu.it;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class PostgresContainerBase {
    static final PostgreSQLContainer<?> PG =
            new PostgreSQLContainer<>("postgres:16")
                    .withDatabaseName("edu_test")
                    .withUsername("edu")
                    .withPassword("edu");

    static {
        PG.start();
    }

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url", PG::getJdbcUrl);
        r.add("spring.datasource.username", PG::getUsername);
        r.add("spring.datasource.password", PG::getPassword);
        r.add("spring.jpa.hibernate.ddl-auto", () -> "update");
        r.add("spring.jpa.open-in-view", () -> "false");
        r.add("spring.jpa.properties.hibernate.enable_lazy_load_no_trans", () -> "false");
    }
}
