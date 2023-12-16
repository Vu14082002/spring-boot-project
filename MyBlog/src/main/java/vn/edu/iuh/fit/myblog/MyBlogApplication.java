package vn.edu.iuh.fit.myblog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "SPRING BOOT APPLICATION BLOG REST API",
                description = "Spring boot application rest api Documentation",
                version = "v0.0.1",
                contact = @Contact(name = "Name :))", email = "Email :))", url = "url....."),
                license = @License(name = "Apache")
        ),
        externalDocs = @ExternalDocumentation(description = "spring boot blog app documentation" )

)
public class MyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
