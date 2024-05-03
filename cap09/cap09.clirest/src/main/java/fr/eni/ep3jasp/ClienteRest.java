package fr.eni.ep3jasp;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;


/**
 * Ejemplo ejemplo sencillo de cliente REST. Se utiliza con el servidor REST del capítulo 9 
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class ClientRest {
  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {
          "applicationContext.xml"
        });

    RestTemplate restTemplate = new RestTemplate();
    Usuario usuario = restTemplate.getForObject("http://localhost:8080/cap09.sr2/usuarios/2",
        Usuario.class);

    log.info(usuario.toString());
  }
}
