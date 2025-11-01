package ru.MarkMoskvitin.NauJava;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NauJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NauJavaApplication.class, args);
	}

    @Bean
    public OpenAPI customOpenAPI(@Value("${app.version}") String appVersion) {
        return new OpenAPI().components(new Components()).info(new Info().title("NauJava API").version(appVersion).license(new License().name("Test")));

    }

}
