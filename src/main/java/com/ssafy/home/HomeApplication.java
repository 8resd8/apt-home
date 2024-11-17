package com.ssafy.home;

import io.awspring.cloud.autoconfigure.s3.S3AutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = {S3AutoConfiguration.class})
@EnableAspectJAutoProxy
public class HomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeApplication.class, args);
	}

}
