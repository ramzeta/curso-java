package fr.eni.ep3jasp.singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Ejemplo singleton
 *
 * @author $author$
 * @version $Revision$
  */
public class Main {
  /**
   * Método main
   *
   * @param args los argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext(
        "aplicacionContexto.xml");

    ServicioUnico s1 = (ServicioUnico) context.getBean("servicioUnico");
    ServicioUnico s2 = (ServicioUnico) context.getBean("servicioUnico");
    s1.setEtat(3);

    System.out.println("Mismo valores (equals) ?: " + s1.equals(s2));
    System.out.println("Misma referencia (==)    ?: " + (s1 == s2));
    System.out.println(s1);
    System.out.println(s2);

    s1.setEstado(2);
    s2.VerEstado();
  }
}
