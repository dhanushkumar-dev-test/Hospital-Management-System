package com.ty.HospitalManagementSystem.util;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI hospitalAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Hospital Management System API")
						.version("1.0")
						.description("Backend APIs for HMS"));
	}
}