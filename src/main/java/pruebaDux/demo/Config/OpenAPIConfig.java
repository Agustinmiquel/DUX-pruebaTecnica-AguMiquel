package pruebaDux.demo.Config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openApiPersonalizada(){
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Prueba Tecnica Dux Software")
                        .version("1.0.3")
                        .description("Prueba técnica para Dux, pruebas para probar API de equipos"));
    }
}
