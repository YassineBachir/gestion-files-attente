package backend.uam.GestionService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Autorise CORS pour toutes les routes
                .allowedOrigins("http://localhost:3000") // Autorise cette origine
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Autorise ces méthodes HTTP
                .allowedHeaders("*") // Autorise tous les headers
                .allowCredentials(true) // Autorise l'envoi de cookies
                .maxAge(3600); // Durée de mise en cache des informations CORS
    }
}
