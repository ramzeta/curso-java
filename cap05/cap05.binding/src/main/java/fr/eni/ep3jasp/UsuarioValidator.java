package fr.eni.ep3jasp;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Validador
 *
 * @author $author$
 * @version $Revision$
  */
public class UsuarioValidator implements Validator {
  /**
   * Supports
   *
   * @param clazz El nombre de la clase
   *
   * @return Igualdad de clases
   */
  public boolean supports(Class clazz) {
    return Usuario.class.equals(clazz);
  }

  /**
   * Validación
   *
   * @param obj El objeto
   * @param e Los errores
   */
  public void validate(Object obj, Errors e) {
    ValidationUtils.rejectIfEmpty(e, "nombre", "nombre.vacio");

    Usuario p = (Usuario) obj;

    if (p.getNombre().contains("s")) {
      e.rejectValue("nombre", "contiene.s");
    } else if (p.getId() < 0) {
      e.rejectValue("id", "id.neg");
    }
  }
}
