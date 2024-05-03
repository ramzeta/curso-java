package fr.eni.ep3jasp;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;


/**
 * Conversión Cadena hacia Usuario usando una factory
 *
 * @author $author$
 * @version $Revision$
  */
public class StringToUsuarioConverterFactory implements ConverterFactory<String, Usuario> {
  /**
   * El conversor
   *
   * @param <T> El tipo
   * @param arg0 el valor
   *
   * @return El objeto devuelto en función del tipo
   */
  @SuppressWarnings("unchecked")
  @Override
  public <T extends Usuario> Converter<String, T> getConverter(
    Class<T> arg0) {
    return (Converter<String, T>) new StringToUsuarioConverter();
  }
}
