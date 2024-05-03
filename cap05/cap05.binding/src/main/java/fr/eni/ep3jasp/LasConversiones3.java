package fr.eni.ep3jasp;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.Arrays;
import java.util.List;


/**
 * Ejemplos de conversión utilizando GenericConversionService para las listas y objetos
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class LasConversiones3 {
  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    GenericConversionService conversionService = new DefaultConversionService(); //ConversionServiceFactory.createDefaultConversionService();

    testToArray(conversionService);
    testToString(conversionService);
    testToObject(conversionService);
  }

  /**
   * Conversión hacia un Array
   *
   * @param conversionService El servicio
   */
  private static void testToArray(GenericConversionService conversionService) {
    List lenguajes = Arrays.asList("C", "C++", "Java");
    String[] stringArray = conversionService.convert(lenguajes, String[].class);

    for (String lenguajes : stringArray) {
      System.out.println("Language is " + lenguajes);
    }
  }

  /**
   * Conversión hacia una cadena de caracteres
   *
   * @param conversionService El servicio
   */
  private static void testToString(GenericConversionService conversionService) {
    List lenguajes = Arrays.asList("C", "C++", "Java");
    String lenguajesAsString = conversionService.convert(lenguajes, String.class);
    System.out.println("All languages -->" + lenguajesAsString);
  }

  /**
   * Conversión hacia un objeto
   *
   * @param conversionService El servicio
   */
  private static void testToObject(GenericConversionService conversionService) {
    conversionService.addConverter(new UsuarioToStringConverter());

    Usuario utilObject = new Usuario(2, "CONNOR", "John");
    String utilAsString = conversionService.convert(new Usuario[] { utilObject },
        String.class);
    System.out.println("Usuario -->" + utilAsString);
  }
}
