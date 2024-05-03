package fr.eni.ep3jasp;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Uso de los logs lombok
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class LosLogs {
  /**
   * El logger
   */
  private static final Logger slf4jLogger = LoggerFactory.getLogger(LosLogs.class);

  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    slf4jLogger.trace("Hola");

    String nombre = "John CONNOR";
    slf4jLogger.debug("Hi, {}", nombre);
    slf4jLogger.info("Log de tipo info.");
    slf4jLogger.warn("Log de tipo warn.");
    slf4jLogger.error("Log de tipo error.");
    log.info("Log de tipo info via lombok, {}", nombre);
  }
}
