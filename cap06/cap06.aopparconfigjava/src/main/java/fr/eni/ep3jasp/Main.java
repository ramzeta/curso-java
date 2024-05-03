package fr.eni.ep3jasp;

import org.aopalliance.aop.Advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;


/**
 * Ejemplo sobre AOP
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
    PojoUn one = new PojoUn();
    PojoDos two = new PojoDos();

    PojoUn proxyUn;
    PojoDos proxyDos;

    Pointcut pc = new SimpleStaticPointcut();
    Advice advice = new SimpleAdvice();
    Advisor advisor = new DefaultPointcutAdvisor(pc, advice);

    ProxyFactory pf = new ProxyFactory();
    pf.addAdvisor(advisor);
    pf.setTarget(one);
    proxyUn = (PojoUn) pf.getProxy();

    pf = new ProxyFactory();
    pf.addAdvisor(advisor);
    pf.setTarget(two);
    proxyDos = (PojoDos) pf.getProxy();

    proxyUn.mas();
    proxyDos.mas();

    proxyUn.menos();
    proxyDos.menos();
  }
}


/**
 * Second Pojo
 *
 * @author $author$
 * @version $Revision$
  */
class PojoDos {
  /**
   * Método más
   */
  public void mas() {
    System.out.println("PojoDos.mas");
  }

  /**
   * Método menos
   */
  public void menos() {
    System.out.println("PojoDos.menos");
  }
}


/**
 * Primer Pojo
 *
 * @author $author$
 * @version $Revision$
  */
class PojoUn {
  /**
   * Método más
   */
  public void mas() {
    System.out.println("PojoUn.mas");
  }

  /**
   * Método menos
   */
  public void menos() {
    System.out.println("PojoUn.menos");
  }
}


/**
 * Advice simple
 *
 * @author $author$
 * @version $Revision$
  */
class SimpleAdvice implements MethodInterceptor {
  /**
   * Invocación
   *
   * @param invocación al método a invocar
   *
   * @return El objeto devuelto
   *
   * @throws Throwable La excepción a enviar
   */
  public Object invoke(MethodInvocation invocation) throws Throwable {
    System.out.println(">>>SimpleAdvice.Invoking " +
      invocation.getMethod().getName());

    Object retVal = invocation.proceed();
    System.out.println(">>>SimpleAdvice.Done");

    return retVal;
  }
}


/**
 * Pointcut estático simple
 *
 * @author $author$
 * @version $Revision$
  */
class SimpleStaticPointcut extends StaticMethodMatcherPointcut {
  /**
   * Método de comparación
   *
   * @param method El método
   * @param cls La clase
   *
   * @return Resultado de la comparación
   */
  public boolean matches(Method method, Class cls) {
    return ("mas".equals(method.getName()));
  }

  /**
   * Recuperación del filtro de clase
   *
   * @return El filtro de clase
   */
  public ClassFilter getClassFilter() {
    return new ClassFilter() {
        public boolean matches(Class cls) {
          return (cls == PojoUn.class);
        }
      };
  }
}
