package fr.eni.ep3jasp;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.TypeMismatchException;

import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;

import java.util.List;


/**
 * Ejemplo de wrapper en los Beans
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class LosBeanWrappers {
  /**
   * Punto de entrada
   *
   * @param args Argumentos de la línea de comandos
   **/	
  public static void main(String[] args) {
    //Uso del BeanWrapper
    Usuario usuario = new Usuario();
    BeanWrapper bw = new BeanWrapperImpl(usuario);
    bw.setPropertyValue("nombre", "John");
    bw.setPropertyValue("apellido", "DO");
    bw.setPropertyValue("id", "200");

    final int id = 200;
    log.info("Verificación:" + (id == usuario.getId()) + "Usuario=" +
      usuario);

    try {
      bw.setPropertyValue("id", "no numérico");
    } catch (TypeMismatchException e) {
      log.info("Err binding :" + e.getMessage());
    }

    //Un poco más complejo
    log.info("MutablePropertyValues:");

    MutablePropertyValues values = new MutablePropertyValues();
    values.addPropertyValue("nombre", "DOs");
    values.addPropertyValue("apellido", "Jane");

    //values.addPropertyValue("id", "2100");
    DataBinder dataBinder = new DataBinder(usuario, "John");
    dataBinder.setAllowedFields(new String[] { "nombre", "apellido" });
    dataBinder.setValidator(new UsuarioValidator());
    dataBinder.bind(values);
    dataBinder.validate();

    log.info("allErrors:");

    List<ObjectError> allErrors = dataBinder.getBindingResult().getAllErrors();

    for (Object object : allErrors) {
      ObjectError error = (ObjectError) object;
      log.info(error.toString());
    }
  }
}
