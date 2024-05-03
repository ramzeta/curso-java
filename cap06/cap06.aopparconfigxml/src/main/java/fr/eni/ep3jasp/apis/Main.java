package fr.eni.ep3jasp.apis;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;


/**
 * Ejemplos de uso de los API de AOP Spring
 *
 * @author $author$
 * @version $Revision$
  */
class MiPointcut implements Pointcut {
  /**
   * El getter filtro de clase
   *
   * @return El filtro de clase
   */
  public ClassFilter getClassFilter() {
    return null;
  }

  /**
   * El getter del método de comparación
   *
   * @return El método de comparación
   */
  public MethodMatcher getMethodMatcher() {
    return null;
  }
}


/**
 * El filtro de clase personalizado
 *
 * @author $author$
 * @version $Revision$
  */
class MiClassFilter implements ClassFilter {
  /**
   * Método de comparación
   *
   * @param clazz La clase
   *
   * @return El resultado de la comparación
   */
  public boolean matches(Class<?> clazz) {
    return false;
  }
}


/**
 * Comparador de método personalizado
 *
 * @author $author$
 * @version $Revision$
  */
class MiMethodMatcher implements MethodMatcher {
  /**
   * El método de comparación
   *
   * @param method El método
   * @param targetClass La clase destino
   *
   * @return El resultado de la comparación
   */
  public boolean matches(Method method, Class<?> targetClass) {
    return false;
  }

  /**
   * Getter flag runtime
   *
   * @return Le flag runtime
   */
  public boolean isRuntime() {
    return false;
  }

  /**
   * Método de comparación
   *
   * @param method El método
   * @param targetClass La clase destino
   * @param args Los argumentos
   *
   * @return El resultado de la comparación
   */
  public boolean matches(Method method, Class<?> targetClass, Object[] args) {
    return false;
  }
}



