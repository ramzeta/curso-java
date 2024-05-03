package fr.eni.ep3jasp;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.List;


/**
 * Ejemplos de las conversiones a través del GenericConversionService para las tablas de Strings
 * createDefaultConversionService está obsoleto pero se muestra para el código antiguo.
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class LasConversiones2 {
  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    GenericConversionService conversionService = new DefaultConversionService(); //ConversionServiceFactory.createDefaultConversionService();

    testToCollection(conversionService);
    testToString(conversionService);
    testToObject(conversionService);
  }

  /**
   * Conversión hacia una colección
   *
   * @param conversionService El servicio
   */
  private static void testToCollection(
    GenericConversionService conversionService) {
    @SuppressWarnings("unchecked")
    List<String> listOfTerm = conversionService.convert(new String[] {
          "t100", "t800", "tx"
        }, List.class);

    for (String term : listOfTerm) {
      System.out.println("Terminator is " + term);
    }
  }

  /**
   * Conversión hacia una cadena de carácteres
   *
   * @param conversionService El servicio
   */
  private static void testToString(GenericConversionService conversionService) {
    String terms = conversionService.convert(new String[] { "t100", "t800", "tx" },
        String.class);
    System.out.println("Terminator is " + terms);
  }

  /**
   * Conversión hacia un objeto
   *
   * @param conversionService El servicio
   */
  private static void testToObject(GenericConversionService conversionService) {
    conversionService.addConverter(new StringToUsuarioConverter());

    Usuario usuario = conversionService.convert(new String[] {
          "5,John,REESE"
        }, Usuario.class);
    System.out.println("El nombre es " + usuario.getNombre());
    System.out.println("El apellido es " + usuario.getApellido());
  }
}
