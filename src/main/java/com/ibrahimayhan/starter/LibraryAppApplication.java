package com.ibrahimayhan.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.ibrahimayhan"})
@ComponentScan(basePackages = {"com.ibrahimayhan"})
@EnableJpaRepositories(basePackages = {"com.ibrahimayhan"})
@EnableFeignClients(basePackages = {"com.ibrahimayhan"})
public class LibraryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryAppApplication.class, args);
	}

}
