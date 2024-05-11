package rj.cefet.sacapi;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "sacAuth", bearerFormat = "JWT", scheme = "bearer", type = SecuritySchemeType.HTTP)
public class SacapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SacapiApplication.class, args);
	}

}
