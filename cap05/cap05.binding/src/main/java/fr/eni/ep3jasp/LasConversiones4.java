package fr.eni.ep3jasp;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;


/**
 * Ejemlos de las conversiones 
 * UsuarioToStringConverter y StringToUsuarioConverter
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class LasConversiones4 {
  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   */

  public static void main(String[] args) {
    test1();
    test2();
    test3();
  }

  /**
   * Conversión de un usuario
   */
  private static void test1() {
    GenericConversionService conversionService = new GenericConversionService();

    Converter<Usuario, String> customConverter = new UsuarioToStringConverter();
    conversionService.addConverter(customConverter);

    try {
      String result = customConverter.convert(null);
      System.out.println("Result is " + result);
    } catch (ConversionFailedException e) {
      log.info(e.toString());
    }
  }

  /**
   * Conversión de un usuario
   */
  private static void test2() {
    GenericConversionService conversionService = new GenericConversionService();

    Converter<Usuario, String> customConverter = new UsuarioToStringConverter();
    conversionService.addConverter(customConverter);

    Usuario article = new Usuario(3, "CONNOR", "Sarah");
    String result = conversionService.convert(article, String.class);
    System.out.println("Result is '" + result + "'");
  }

  /**
   * Conversión de un usuario
   */
  private static void test3() {
    GenericConversionService conversionService = new GenericConversionService();

    Converter<String, Usuario> customConverter = new StringToUsuarioConverter();
    conversionService.addConverter(customConverter);

    String articleAsString = new String("2,Kyle,REESE");
    Usuario result = conversionService.convert(articleAsString,
        Usuario.class);
    System.out.println("El nombre es " + result.getNombre());
    System.out.println("El apellido es " + result.getApellido());
  }
}
