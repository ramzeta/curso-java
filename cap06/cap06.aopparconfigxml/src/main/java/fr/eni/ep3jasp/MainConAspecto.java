package fr.eni.ep3jasp;

import org.aopalliance.aop.Advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;


/**
 * Ejemplo con un aspecto
 *
 * @author $author$
 * @version $Revision$
  */
interface Pojo3 {
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
class SimplePojo3 implements Pojo3 {
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
class RetryAdvice implements AfterReturningAdvice {
  /**
   * DOCUMENT ME!
   *
   * @param returnValue DOCUMENT ME!
   * @param method DOCUMENT ME!
   * @param args DOCUMENT ME!
   * @param target DOCUMENT ME!
   *
   * @throws Throwable La excepción a enviar
   */
  public void afterReturning(Object returnValue, Method method, Object[] args,
    Object target) throws Throwable {
    System.out.println("after " + method.getName());
  }
}


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
public class MainConAspecto {
  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    ProxyFactory factory = new ProxyFactory(new SimplePojo3());
    Class<?> intf = Pojo3.class;
    factory.addInterface(intf);

    Advice advice = new RetryAdvice();
    factory.addAdvice(advice);
    factory.setExposeProxy(true);

    Pojo3 pojo = (Pojo3) factory.getProxy();

    pojo.foo();
    pojo.bar();
  }
}
