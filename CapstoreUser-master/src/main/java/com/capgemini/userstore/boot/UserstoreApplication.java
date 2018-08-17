package com.capgemini.userstore.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages= {"com.capgemini.store"})
@EntityScan(basePackages="com.capgemini.store.beans")
@EnableJpaRepositories(basePackages="com.capgemini.store.reposervices")
@EnableWebMvc
public class UserstoreApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(UserstoreApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		return builder.sources(UserstoreApplication.class);
	}
}
