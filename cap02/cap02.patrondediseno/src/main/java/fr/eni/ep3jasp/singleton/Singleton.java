package fr.eni.ep3jasp.singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Ejemplo singleton simple!
 *
 * @author $author$
 * @version $Revision$
  */
public class Singleton {
  /**
   * Punto de entrada
   *
   * @param args Argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext(
        "applicationContext.xml");

    String s1 = (String) context.getBean("miString");
    String s2 = (String) context.getBean("miString");

    System.out.println("Mismo valores (equals) ?: " + s1.equals(s2));
    System.out.println("Misma referencia (==)    ?: " + (s1 == s2));
    System.out.println(s1);
    System.out.println(s2);
  }
}
