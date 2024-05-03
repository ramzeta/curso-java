package fr.eni.ep3jasp;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.support.GenericConversionService;


/**
 * Conversión utilizando factories 
 * UsuarioToStringConverterFactory y StringToUsuarioConverterFactory
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class Conversiones6 {
  /**
   * Punta de entrada
   *
   * @param args Argumentos de la línea de comandos
   */	
	public static void main(String[] args) {
    GenericConversionService conversionService = new GenericConversionService();

    conversionService.addConverterFactory(new UsuarioToStringConverterFactory());
    conversionService.addConverterFactory(new StringToUsuarioConverterFactory());

    String usuarioAsString = "5,Janelle,VOIGHT";
    Usuario usuario = conversionService.convert(usuarioAsString,Usuario.class);
    System.out.println("El nombre es " + usuario.getNombre());
    System.out.println("El apellido es " + usuario.getApellido());

    usuarioAsString = conversionService.convert(usuario, String.class);
    System.out.println("Usuario en cadena: [" + usuarioAsString + "]");
  }
}
