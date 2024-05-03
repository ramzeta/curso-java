package fr.eni.ep3jasp;

import org.springframework.format.Printer;

import java.util.Locale;


/**
 * El printer
 *
 * @author $author$
 * @version $Revision$
  */
public class TarjetaDeCreditoPrinter implements Printer {
  /**
   * Visualización del número
   *
   * @param cc La tarjeta de crédito
   * @param locale El locale
   *
   * @return El número en forma de cadena de caracteres
   */
  @Override
  public String print(Object cc, Locale locale) {
    TarjetaDeCredito objet = (TarjetaDeCredito) cc;

    return objet.toString();
  }
}
