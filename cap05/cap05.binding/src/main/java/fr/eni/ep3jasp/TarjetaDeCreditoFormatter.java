package fr.eni.ep3jasp;

import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.text.ParseException;

import java.util.Locale;


/**
 * Formatter de tarjera de crédito
 *
 * @author $author$
 * @version $Revision$
  */
public class TarjetaDeCreditoFormatter implements Formatter {
  /**
   * El parser
   */
  private Parser parser;

  /**
   * El printer
   */
  private Printer printer;

  /**
   * Creación de un formateador de tarjeta de crédito
   *
   * @param parser El parser
   * @param printer El printer
   */
  public TarjetaDeCreditoFormatter(Parser parser, Printer printer) {
    this.parser = parser;
    this.printer = printer;
  }

  /**
   * Parse del número de la tarjeta de crédito
   *
   * @param cc Carjeta de crédito
   * @param l el locale
   *
   * @return Una tarjeta de crédito
   *
   * @throws ParseException Excepción generada
   */
  @Override
  public TarjetaDeCredito parse(String cc, Locale l) throws ParseException {
    return (TarjetaDeCredito) parser.parse(cc, l);
  }

  /**
   * Printer
   *
   * @param cc La tarjeta de crédito
   * @param l El locale
   *
   * @return El número de tarjeta de crédito en una cadena de caracteres
   */
  @Override
  public String print(Object cc, Locale l) {
    return printer.print(cc, l);
  }
}
