package fr.eni.ep3jasp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.apache.logging.log4j.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex1Logs implements CommandLineRunner {
  //Logback
  private static final Logger LOGGER = LoggerFactory.getLogger(Ex1Logs.class);
  //Log4j
  //private static Logger LOGGER = LogManager.getLogger(Ex1Logs.class);

  public static void main(String[] args) {
    SpringApplication.run(Ex1Logs.class, args);
  }
  @Override
  public void run(String... args) throws Exception {
    LOGGER.trace("Prueba de los logs:trace");
    LOGGER.debug("Prueba de los logs:debug");
    LOGGER.info("Prueba de los logs:info");
    LOGGER.warn("Prueba de los logs:warn");
    LOGGER.error("Prueba de los logs:error");

    LOGGER.info(Colorise.GREEN_BOLD + "Texto en verde negrita" + Colorise.RESET);
    LOGGER.info(Colorise.RED + "Texto en rojo" + Colorise.RESET);
    LOGGER.info(Colorise.GREEN_BACKGROUND + Colorise.RED + "Texto en rojo sobre fondo verde" + Colorise.RESET);
  }
}
