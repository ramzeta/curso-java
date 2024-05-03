package fr.eni.ep3jasp;

import lombok.extern.slf4j.Slf4j;

import org.springframework.format.support.FormattingConversionService;


/**
 * Ejemplos de las conversiones de un número de tarjeta de crédito
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class LasConversiones7 {
  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    FormattingConversionService service = new FormattingConversionService();
    TarjetaDeCreditoParser parser = new TarjetaDeCreditoParser();
    TarjetaDeCreditoPrinter printer = new TarjetaDeCreditoPrinter();
    service.addFormatterForFieldType(TarjetaDeCredito.class, printer, parser);
    test1(service);
    test2(service);
  }

  /**
   * Conversión número tarjeta de crédito desde una cadena de caracteres
   *
   * @param service El servicio
   */
  private static void test1(FormattingConversionService service) {
    String cc = "1111-2222-3333-4444";
    TarjetaDeCredito o = (TarjetaDeCredito) service.convert(cc, TarjetaDeCredito.class);
    log.info(o.toString());
  }

  /**
   * Conversión de un número de tarjeta de crédito desde una serie de números
   *
   * @param service El servicio
   */
  private static void test2(FormattingConversionService service) {
    TarjetaDeCredito o = new TarjetaDeCredito(1111, 2222, 3333, 4444);
    String cc = service.convert(o, String.class);
    log.info("El número de la tarjeta de crédito es:" + cc);
  }
}
