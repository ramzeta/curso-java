package fr.eni.ep3jasp.proxy;

import org.aopalliance.aop.Advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;


/**
 * La interfaz del pojo simple
 *
 * @author $author$
 * @version $Revision$
  */
interface Pojo3 {
  /**
   * Método que se intercepta
   */
  public void foo();
}


/**
 * Un pojo simple
 *
 * @author $author$
 * @version $Revision$
  */
class SimplePojo3 implements Pojo3 {
  /**
   * Método que muestra foo.
   */
  public void foo() {
    System.out.println("foo");
  }
}


/**
 * Método retry
 *
 * @author $author$
 * @version $Revision$
  */
class RetryAdvice implements AfterReturningAdvice {
  /**
   * Método que se llama después de la llamada normal
   *
   * @param returnValue Nuevo valor a devolver
   * @param method Método a desviar
   * @param args Argumentos del método a desviar
   * @param target Objeto destino del devío
   *
   * @throws Throwable Excepción generada
   */
  public void afterReturning(Object returnValue, Method method, Object[] args,
    Object target) throws Throwable {
    System.out.println("Después " + method.getName());
  }
}


/**
 * Control con un aspecto
 *
 * @author $author$
 * @version $Revision$
  */
public class MainConAspecto {
  /**
   * Punto de entrada
   *
   * @param args Argumentos de la línea de comandos
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
  }
}
