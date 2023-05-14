package com.insutek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com/insutek/server/repository")
public class ServerApplication {

	private static final Logger logger = LoggerFactory.getLogger(ServerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		logger.info("*************SERVER STARTED!!!*************");

	}

}
