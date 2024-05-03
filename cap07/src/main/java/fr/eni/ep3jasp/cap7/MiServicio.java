package fr.eni.ep3jasp.cap7;

import org.springframework.core.env.Environment;


/**
 * Interfaz de mi servicio
 *
 * @author $author$
 * @version $Revision$
  */
public interface MiServicio {
  /**
   * setter del entorno
   *
   * @param env el entorno
   */
  void setEnvironment(Environment env);

  /**
   * getter del entorno
   *
   * @return el entorno
   */
  Environment getEnvironment();
}
