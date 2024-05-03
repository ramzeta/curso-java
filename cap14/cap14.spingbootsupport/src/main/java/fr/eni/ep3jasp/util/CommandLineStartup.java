package fr.eni.ep3jasp.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommandLineStartup implements CommandLineRunner {
  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CommandLineStartup.class);

  @Override
  public void run(String... args) throws Exception {
    log.info("CommandLineRunner:La aplicación ha arrancado con estos argumentos: {}", Arrays.toString(args));
  }
}