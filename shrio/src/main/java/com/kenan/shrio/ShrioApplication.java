package com.kenan.shrio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath*:applicationContext*" })
public class ShrioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShrioApplication.class, args);
	}

}
