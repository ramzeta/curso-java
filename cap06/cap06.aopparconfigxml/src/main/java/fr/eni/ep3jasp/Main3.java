package fr.eni.ep3jasp;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
interface Pojo4 {
  /**
   * DOCUMENT ME!
   */
  public void foo();

  /**
   * DOCUMENT ME!
   */
  public void bar();
}


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
class MiSimplePojo implements Pojo4 {
  /**
   * DOCUMENT ME!
   */
  public void foo() {
    System.out.println("foo");
    this.bar();
  }

  /**
   * DOCUMENT ME!
   */
  public void bar() {
    System.out.println("bar");
  }
}


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
public class Main3 {
  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    Pojo4 pojo = new MiSimplePojo();

    // this is a direct method call on the pojo reference
    pojo.foo();
  }
}
