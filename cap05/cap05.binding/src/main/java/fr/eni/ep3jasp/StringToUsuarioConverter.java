package fr.eni.ep3jasp;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;


/**
 * Ejemplo de conversión cadena a usuario
 *
 * @author $author$
 * @version $Revision$
  */
public class StringToUsuarioConverter implements Converter<String, Usuario> {
  /**
   * Conversión
   *
   * @param UsuarioAsString Usuario en forma de String
   *
   * @return El usuario
   */
  @Override
  public Usuario convert(String UsuarioAsString) {
    if (UsuarioAsString == null) {
      throw new ConversionFailedException(TypeDescriptor.valueOf(String.class),
        TypeDescriptor.valueOf(String.class), UsuarioAsString, null);
    }

    String[] tempArray = UsuarioAsString.split(",");
    Usuario article = new Usuario(Integer.parseInt(tempArray[0]),
        tempArray[1], tempArray[2]);

    return article;
  }
}
