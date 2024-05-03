package fr.eni.ep3jasp;

import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.List;


/**
 * Ejemplo de conversión utilizando el conversor integrado hacia un Array
 *
 * @author $author$
 * @version $Revision$
  */
public class ConvIntegradoHaciaArray {
  /**
   * Punto de entrada
   *
   * @param args Argumentos de la línea de comandos
   */	
  public static void main(String[] args) {
    //Spring 3.2
    GenericConversionService conversionService = new DefaultConversionService(); //ConversionServiceFactory.createDefaultConversionService();

    testToCollection(conversionService);
    testToString(conversionService);
    testToObject(conversionService);
  }

  /**
   * Conversión hacia una colección
   *
   * @param conversionService El servicio de conversión
   */
  private static void testToCollection(
    GenericConversionService conversionService) {
    @SuppressWarnings("unchecked")
    List<String> listaDeColores = conversionService.convert(new String[] {
          "Rojo", "Verde", "Azul"
        }, List.class);

    for (String color : listaDeColores) {
      System.out.println("El color es " + color);
    }
  }

  /**
   * Conversión hacia una cadena de caracteres
   *
   * @param conversionService El servicio de conversión
   */
  private static void testToString(GenericConversionService conversionService) {
    String colores = conversionService.convert(new String[] {
          "Rojo", "Verde", "Azul"
        }, String.class);
    System.out.println("El color es " + colores);
  }

  /**
   * Conversión hacia un objeto
   *
   * @param conversionService El servicio de conversion
   */
  private static void testToObject(GenericConversionService conversionService) {
    conversionService.addConverter(new StringToUsuarioConverter());

    Usuario article = conversionService.convert(new String[] { "1,John,SMITH" },
        Usuario.class);
    System.out.println("El nombre es:" + article.getNombre());
    System.out.println("El apellido es:" + article.getApellido());
  }
}
