package fr.eni.ep3jasp.proxy.simples;

import lombok.extern.java.Log;


/**
 * el singleton clásico antes de las enum
 *
 * @author $author$
 * @version $Revision$
  */
class MiSingleton {
  /**
   * La instancia del singleton
   */
  private static MiSingleton UNIQUE = null;

  /**
   * Creación de un nuevo MiSingleton.
   */
  private MiSingleton() {
  }

  /**
   * Devuelve la instancia del singleton, si todavía no existe la crea
   *
   * @return La instancia del singleton
   */
  public static synchronized MiSingleton getInstance() {
    if (UNIQUE == null) {
      UNIQUE = new MiSingleton();
    }

    return UNIQUE;
  }
}


/**
 * Punto de entrada
 *
 * @author $author$
 * @version $Revision$
  */
@Log
public class Main {
  /**
   * Punto de entrada
   *
   * @param args Argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    MiSingleton s1 = MiSingleton.getInstance();
    MiSingleton s2 = MiSingleton.getInstance();

    boolean test = s1 == s2;
    log.info("s1==s2:" + test);
  }
}
