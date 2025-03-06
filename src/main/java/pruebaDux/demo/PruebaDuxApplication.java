package pruebaDux.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Prueba Tecnica Dux Software",
				version = "1.0.3",
				description = "Prueba técnica para Dux"
		)
)
public class PruebaDuxApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaDuxApplication.class, args);
	}

}
