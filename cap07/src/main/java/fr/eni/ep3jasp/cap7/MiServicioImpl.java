package fr.eni.ep3jasp.cap7;

import org.springframework.core.env.Environment;


/**
 * Implementación de mi service
 *
 * @author $author$
 * @version $Revision$
  */

public class MiServicioImpl implements MiServicio {
  /**
   * setter del entorno
   *
   * @param env el entorno
   */
  @Override
  public void setEnvironment(Environment env) {
    System.out.println(env.getProperty("M4_HOME").toString());
  }

  /**
   * getter del entorno
   *
   * @return el entorno
   */
  @Override
  public Environment getEnvironment() {
    return null;
  }
}
