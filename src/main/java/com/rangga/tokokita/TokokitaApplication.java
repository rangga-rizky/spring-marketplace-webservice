package com.rangga.tokokita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TokokitaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokokitaApplication.class, args);
	}
}
