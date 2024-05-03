package fr.eni.ep3jasp;

import lombok.extern.java.Log;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConversionServiceFactory;
import org.springframework.core.convert.support.GenericConversionService;

import org.springframework.format.Formatter;
import org.springframework.format.datetime.DateFormatter;
//import org.springframework.format.number.NumberFormatter;

import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Ejemplos de los formateadores de fechas y números
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class LasConversiones6 {
  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   *
   * @throws Exception Excepción generada
   */
  public static void main(String[] args) throws Exception {
    testDateFormatter();
    testNumberFormatter();
  }

  /**
   * Formateado de la fecha
   */
  private static void testDateFormatter() {
    Formatter dateFormatter = new DateFormatter();
    String dateAsString = dateFormatter.print(new Date(), Locale.FRANCE);
    System.out.println("Fecha en Francia: " + dateAsString);
  }

  /**
   * Formateado del número
   *
   * @throws Exception Excepción generada
   */
  private static void testNumberFormatter() throws Exception {
    NumberFormatter doubleFormatter = new NumberFormatter();
    DecimalFormat df = new DecimalFormat("#####.###"); //  doubleFormatter.setPattern("#####.###");
//;
    String number = df.format(12345.6789d); //doubleFormatter.print(new Double(12345.6789d), Locale.FRANCE);
    System.out.println("Número: " + number);
  }
}
