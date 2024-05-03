package fr.eni.ep3jasp.aop2;

import org.aopalliance.aop.Advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;


/**
 * Interfaz del objeto de demostración Pojo
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
 * Implementación del objeto de demostracción Pojo
 *
 * @author $author$
 * @version $Revision$
  */
class SimplePojo implements Pojo {
  /**
   * Método foo
   */
  public void foo() {
    System.out.println("foo");
    this.bar();
  }

  /**
   * Método bar
   */
  public void bar() {
    System.out.println("bar");
  }
}


/**
 * Método que se ejecutará después del interceptado
 *
 * @author $author$
 * @version $Revision$
  */
class RetryAdvice implements AfterReturningAdvice {
  /**
   * Código que se llama antes de devolver el control
   *
   * @param returnValue Valor a devolver
   * @param method Método a llamar 
   * @param args Argumentos de la línea de comandos 
   * @param target El destino
   *
   * @throws Throwable La excepción a enviar
   */
  public void afterReturning(Object returnValue, Method method, Object[] args,
    Object target) throws Throwable {
    System.out.println("after " + method.getName());
  }
}


/**
 * Este ejemplo muestra la programación por aspectos usando la configuración por java
 *
 * @author $author$
 * @version $Revision$
  */
public class Main {
  /**
   * Punto de entrada
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
