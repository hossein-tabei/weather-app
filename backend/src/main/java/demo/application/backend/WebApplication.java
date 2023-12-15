package demo.application.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		System.out.println("SpringVersion.getVersion():"+SpringVersion.getVersion());
		System.out.println("SpringBootVersion.getVersion():"+SpringBootVersion.getVersion());
		
		SpringApplication.run(WebApplication.class, args);
	}

}
