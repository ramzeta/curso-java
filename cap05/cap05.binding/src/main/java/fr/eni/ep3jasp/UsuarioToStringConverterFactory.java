package fr.eni.ep3jasp;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;


/**
 * La factory
 *
 * @author $author$
 * @version $Revision$
  */
public class UsuarioToStringConverterFactory implements ConverterFactory<Usuario, String> {
  /**
   * El conversor
   *
   * @param <T> El tipo
   * @param arg0 el usuario
   *
   * @return El objeto convertido en función del tipo
   */
  @Override
  public <T extends String> Converter<Usuario, T> getConverter(
    Class<T> arg0) {
    return (Converter<Usuario, T>) new UsuarioToStringConverter();
  }
}
