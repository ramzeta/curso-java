package fr.eni.spring5.jsf.config;

import fr.eni.spring5.jsf.services.TareaDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:spring/db.xml")
@ComponentScan(basePackages = "fr.eni.spring5")
public class SpringCoreConfig {
    @Bean
    public TareaDao TareaDao() {
        return new TareaDao();
    }
}

