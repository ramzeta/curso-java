package fr.eni.ep3jasp;

import org.springframework.format.Parser;

import java.text.ParseException;

import java.util.Locale;


/**
 * El parser de tarjeta de crédito
 *
 * @author $author$
 * @version $Revision$
  */
public class TarjetaDeCreditoParser implements Parser {
  /**
   * parsing
   *
   * @param cc La tarjeta de crédito
   * @param locale El locale
   *
   * @return Una tarjeta de crédito
   *
   * @throws ParseException Excepción generada
   */
  @Override
  public TarjetaDeCredito parse(String cc, Locale locale)
    throws ParseException {
    String[] caracteres = cc.split("-");

    if ((caracteres == null) || (caracteres.length != 4)) {
      throw new org.springframework.expression.ParseException(-1,
        "Invalid format");
    }

    TarjetaDeCredito c = new TarjetaDeCredito();
    c.setPrimeraTupla(Integer.parseInt(caracteres[0]));
    c.setSegundaTupla(Integer.parseInt(caracteres[1]));
    c.setTerceraTupla(Integer.parseInt(caracteres[2]));
    c.setCuartaTupla(Integer.parseInt(caracteres[3]));

    return c;
  }
}
