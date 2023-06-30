package com.lspeixotodev.blogrestapi;

import com.lspeixotodev.blogrestapi.entity.Role;
import com.lspeixotodev.blogrestapi.repository.RoleRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
public class BlogRestApiApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);

		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);
	}
}
