package fr.eni.ep3jasp;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.List;


/**
 * Ejemplos de las conversiones de los tipos actuales
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class LasConversiones {
  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    GenericConversionService conversionService = new DefaultConversionService(); //ConversionServiceFactory. createDefaultConversionService();

    testToArray(conversionService);
    testToCollection(conversionService);
    testToBoolean(conversionService);
    testToCharacter(conversionService);
    testToNumber(conversionService);
    testToEnum(conversionService);
  }

  /**
   * Conversión hacia un Array
   *
   * @param conversionService El servicio
   */
  private static void testToArray(GenericConversionService conversionService) {
    String[] stringArray = conversionService.convert("Uno,Dos,Tres",
        String[].class);

    for (String elemento : stringArray) {
      log.info("Los elementos: " + elemento);
    }
  }

  /**
   * Conversión hacia una colección
   *
   * @param conversionService El servicio
   */
  private static void testToCollection(
    GenericConversionService conversionService) {
    @SuppressWarnings("unchecked")
    List<String> listOfStrings = conversionService.convert("Uno,dos,Tres",
        List.class);

    for (String elemento : listOfStrings) {
      log.info("Los elementos " + elemento);
    }
  }

  /**
   * Conversión hacia un buleano
   *
   * @param conversionService El servicio
   */
  private static void testToBoolean(GenericConversionService conversionService) {
    Boolean data = null;

    data = conversionService.convert("true", Boolean.class);
    log.info("El valor del buleano es " + data);

    data = conversionService.convert("no", Boolean.class);
    log.info("El valor del buleano es " + data);
  }

  /**
   * Conversión hacia un caracter
   *
   * @param conversionService El servicio
   */
  private static void testToCharacter(
    GenericConversionService conversionService) {
    Caracter data = null;

    data = conversionService.convert("A", Caracter.class);
    log.info("El carácter es " + data);

    /*
                    Exception in thread "main" org.springframework.core.convert.ConversionFailedException: Failed to convert from type java.lang.String to type java.lang.Character for value 'Exception'; nested exception is java.lang.IllegalArgumentException: Can only convert a [String] with length of 1 to a [Character]; string value 'Exception'  has length of 9
    */
    try {
      data = conversionService.convert("Exception", Caracter.class);
      log.info("El carácter es " + data);
    } catch (ConversionFailedException e) {
      log.info("Vemos el catcher de la excepción ConversionFailedException:" + e);
    }
  }

  /**
   * Conversión hacia un Number
   *
   * @param conversionService El servicio
   */
  private static void testToNumber(GenericConversionService conversionService) {
    Integer intData = conversionService.convert("124", Integer.class);
    log.info("El valor del entero es " + intData);

    Float floatData = conversionService.convert("215f", Float.class);
    log.info("El valor del float es " + floatData);
  }

  /**
   * Conversión hacia una enumeración
   *
   * @param conversionService El servicio
   */
  private static void testToEnum(GenericConversionService conversionService) {
    TaskStatus taskStatus = conversionService.convert("PENDING",
        TaskStatus.class);
    log.info("El estado de la tarea es " + taskStatus);
  }
}
