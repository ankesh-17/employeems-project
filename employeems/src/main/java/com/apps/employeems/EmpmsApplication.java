package com.apps.employeems;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class EmpmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpmsApplication.class, args);
	}

	@Bean
	public Docket api() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.apps.employeems")).paths(PathSelectors.any()).build();
		return docket;
	}

	@Bean
	public ApiInfo apiInfo() {
		ApiInfo info = new ApiInfoBuilder().title("Employee management application")
				.description("rest api for employee management").build();
		return info;
	}
}
