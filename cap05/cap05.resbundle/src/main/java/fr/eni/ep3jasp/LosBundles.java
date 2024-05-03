package fr.eni.ep3jasp;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Locale;


/**
 * Clase que muestra el uso de los bundles para localizar las frases.
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class LosBundles {
  /**
   * Punto de entrada
   *
   * @param args Argumentos de la línea de comandos
   */
  public static void main(String[] args) throws SQLException {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        "applicationContext.xml");

    BeanFactory factory = (BeanFactory) context;

    String name = context.getMessage("usuario.nombre",
        new Object[] { "John", 25, "Dallas" }, Locale.US);

    log.info("User name (English) : " + name);

    name = context.getMessage("usuario.nombre",
        new Object[] { "John", 25, "Dallas" }, Locale.US);

    log.info("Nombre usuario (Español) : " + name);

    ResourceHelperService svc = (RResourceHelperService) context.getBean(
        "resourceHelperService");
    svc.VerMessages();
  }
}
