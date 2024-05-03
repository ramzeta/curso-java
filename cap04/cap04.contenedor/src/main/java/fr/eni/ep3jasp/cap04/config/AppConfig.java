package fr.eni.ep3jasp.cap04.config;

import fr.eni.ep3jasp.cap04.services.MyService;
import fr.eni.ep3jasp.cap04.services.MyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("fr.eni.cap04") // search the com.company package for @Component classes
public class AppConfig {

  @Bean(name="myServiceByConfiguration")
  public MyService myService () {
    return new MyServiceImpl();
  }

}