package com.example.finanzasgrupo5backend;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@SpringBootApplication
public class FinanzasGrupo5BackEndApplication {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/**")
                        .allowedOrigins("*") //aquí va el link de tu frontend desplegado
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Permitir los métodos necesarios
                        .allowedHeaders("*"); // Permitir todos los encabezados
            }
        };
    }

    /**
     * Configures and initializes the ModelMapper bean.
     *
     * @return A ModelMapper bean for mapping objects.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * The main method that starts the Finanzas application.
     *
     * @param args Command line arguments.
     */

    public static void main(String[] args) {
        SpringApplication.run(FinanzasGrupo5BackEndApplication.class, args);
    }


}
