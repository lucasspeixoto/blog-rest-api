package com.lspeixotodev.blogrestapi;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "My Blog App REST APIs",
				description = "My Blog App REST APIs Documentation with Spring Boot",
				version = "v1.0",
				contact = @Contact(
						name = "Lucas Peixoto",
						email = "lspeixotodev@gmail.com",
						url = "https://lucasspeixoto.github.io/profile/#/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.javaguides.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "My Blog App REST APIs Repository",
				url = "https://github.com/lucasspeixoto/blog-rest-api"
		)
)
public class BlogRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogRestApiApplication.class, args);
	}

}
