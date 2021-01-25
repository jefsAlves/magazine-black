package com.alvesjefs.magazine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MagazineMsCategoriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagazineMsCategoriesApplication.class, args);
	}

}
