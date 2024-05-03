package fr.eni.ep3jasp;

import org.springframework.core.convert.support.GenericConversionService;


/**
 * Ejemplos de las conversiones con UsuarioToStringConverterFactory y 
 * StringToUsuarioConverterFactory 
 *
 * @author $author$
 * @version $Revision$
  */
public class LasConversiones5 {
  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    GenericConversionService conversionService = new GenericConversionService();

    conversionService.addConverterFactory(new UsuarioToStringConverterFactory());
    conversionService.addConverterFactory(new StringToUsuarioConverterFactory());

    String usuarioAsString = "7,Sarah,CONNOR";
    Usuario usuario = conversionService.convert(usuarioAsString,
        Usuario.class);

    System.out.println("El nombre es " + usuario.getNombre());
    System.out.println("El apellido es " + usuario.getApellido());

    usuarioAsString = conversionService.convert(usuario, String.class);
    System.out.println("Usuarios en forma de cadena [" +
      usuarioAsString + "]");
  }
}
