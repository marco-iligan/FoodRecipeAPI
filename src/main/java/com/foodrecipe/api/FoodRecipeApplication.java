package com.foodrecipe.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Food Recipe API",
				version = "1.0.0",
				description = "This is for learning only",
				contact = @Contact(
						name = "Marco Iligan",
						email = "miligan17@gmail.com"
				)
		)
)
@SecurityScheme(
		name = "token",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer"
)
@SpringBootApplication
public class FoodRecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodRecipeApplication.class, args);
	}

}
