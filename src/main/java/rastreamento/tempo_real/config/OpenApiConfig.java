package rastreamento.tempo_real.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api de Rastreamento de Entregas")
                        .description("Sistema de rastreamento de entregas em tempo real com WebSocket")
                        .version("1.0"));
    }
}