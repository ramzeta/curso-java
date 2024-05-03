package fr.eni.ep3jasp.util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class BeforeStartup implements ApplicationRunner {
  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BeforeStartup.class);

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("ApplicationRunner:La aplicación ha arrancado con estas opciones: {}", args.getOptionNames());
  }

}
