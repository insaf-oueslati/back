
package event.SpringBootApp;  // Placez-le dans un package de configuration approprié

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Webconfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Appliquer les règles sur les endpoints /api/
                .allowedOrigins("http://localhost:4200")  // Origine Angular en développement
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Méthodes HTTP autorisées
                .allowedHeaders("Content-Type", "Authorization")  // Autorise des en-têtes spécifiques
                .allowCredentials(true);  // Permet l'utilisation de cookies/credentials
    }
}


