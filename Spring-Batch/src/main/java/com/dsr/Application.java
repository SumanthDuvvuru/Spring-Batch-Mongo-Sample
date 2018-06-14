package com.dsr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = { DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class })
@ComponentScan("com.dsr")
@EnableMongoRepositories(basePackages = "com.dsr")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
