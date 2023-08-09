package com.ead.userauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.ead.userauth", "com.ead.commonlib"})
public class UserAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthApplication.class, args);
	}

}
