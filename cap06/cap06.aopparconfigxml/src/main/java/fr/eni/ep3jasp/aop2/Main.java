package fr.eni.ep3jasp.aop2;

import org.aopalliance.aop.Advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;


/**
 * La interfaz de Pojo
 *
 * @author $author$
 * @version $Revision$
  */
interface Pojo {
  /**
   * Método foo
   */
  public void foo();

  /**
   * Método bar
   */
  public void bar();
}


/**
 * Implementación de Pojo
 *
 * @author $author$
 * @version $Revision$
  */
class SimplePojo implements Pojo {
  /**
   * El método foo
   */
  public void foo() {
    System.out.println("foo");
    this.bar();
  }

  /**
   * El método bar
   */
  public void bar() {
    System.out.println("bar");
  }
}


/**
 * Observe que se ejecuta después del retorno del método interceptado
 *
 * @author $author$
 * @version $Revision$
  */
class RetryAdvice implements AfterReturningAdvice {
  /**
   * Después del return
   *
   * @param returnValue valor devuelto
   * @param method Método llamado
   * @param args Argumentos
   * @param target Destino
   *
   * @throws Throwable La excepción a enviar
   */
  public void afterReturning(Object returnValue, Method method, Object[] args,
    Object target) throws Throwable {
    System.out.println("after " + method.getName());
  }
}


/**
 * Ejemplo que usa AOP configurado por los archivos xml
 *
 * @author $author$
 * @version $Revision$
  */
public class Main {
  /**
   * Puntos de entrada
   *
   * @param args Los argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    ProxyFactory factory = new ProxyFactory(new SimplePojo());
    Class<?> intf = Pojo.class;
    factory.addInterface(intf);

    Advice advice = new RetryAdvice();
    factory.addAdvice(advice);
    factory.setExposeProxy(true);

    Pojo pojo = (Pojo) factory.getProxy();

    pojo.foo();
    pojo.bar();
  }
}
