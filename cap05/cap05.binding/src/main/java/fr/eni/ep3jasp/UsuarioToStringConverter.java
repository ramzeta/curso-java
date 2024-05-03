package fr.eni.ep3jasp;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;


/**
 * Conversión usuario a String
 *
 * @author $author$
 * @version $Revision$
  */
public class UsuarioToStringConverter implements Converter<Usuario, String> {
  /**
   * Conversor
   *
   * @param usuario El usuario
   *
   * @return La cadena de caracteres
   */
  @Override
  public String convert(Usuario usuario) {
    if (usuario == null) {
      throw new ConversionFailedException(TypeDescriptor.valueOf(
          Usuario.class), TypeDescriptor.valueOf(String.class), usuario,
        null);
    }

    StringBuilder builder = new StringBuilder();
    builder.append(usuario.getId());
    builder.append(",");
    builder.append(usuario.getNombre());
    builder.append(",");
    builder.append(usuario.getApellido());

    return builder.toString();
  }
}
