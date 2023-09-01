package com.edu.iuh.fit.springboot;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.hibernate.dialect.MySQLDialect;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring boot REST API Documentation",
				description = "Spring boot REST API Documentation",
				version = "1.0.0",
				contact = @Contact(
						name = "SherlockVuFullsnack",
						email = "xxxyyyzz@gmail.com",
						url = "https://www.youtube.com/"
				),
				license = @License(
						name = "Demo License ",
						url = "https://www.youtube.com/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "spring boot User Management Documentation",
				url = "https://www.youtube.com/"
		)

)
public class SpringbootRestfullWebservicesApplication {
//	http://localhost:8080/swagger-ui/index.html#/
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfullWebservicesApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
