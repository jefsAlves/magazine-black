package com.alvesjefs.magazine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MagazineEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagazineEurekaServerApplication.class, args);
	}

}
