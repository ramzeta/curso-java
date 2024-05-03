package fr.eni.ep3jasp.pointcut;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  *
 * @param <T> DOCUMENT ME!
 */
interface Sample<T> {
  /**
   * DOCUMENT ME!
   *
   * @param param DOCUMENT ME!
   */
  void sampleGenericMethod(T param);

  /**
   * DOCUMENT ME!
   *
   * @param param DOCUMENT ME!
   */
  void sampleGenericCollectionMethod(Collection<T> param);
}


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
@Component
@Aspect
public class VerificarAntes {
  /**
   * DOCUMENT ME!
   */
  private static final Logger logger = LoggerFactory.getLogger(VerificarAntes.class);

  /**
   * DOCUMENT ME!
   *
   * @param param DOCUMENT ME!
   */
  @Before("execution(* ..Sample+.sampleGenericMethod(*)) && args(param)")
  public void beforeSampleMethod(MyType param) {
    // Advice implementation
  }

  /**
   * DOCUMENT ME!
   *
   * @param param DOCUMENT ME!
   */
  @Before("execution(* ..Sample+.sampleGenericCollectionMethod(*)) && args(param)")
  public void beforeSampleMethod(Collection<MyType> param) {
    // Advice implementation
  }

  /**
   * DOCUMENT ME!
   *
   * @param param DOCUMENT ME!
   */
  @Before("execution(* ..Sample+.sampleGenericCollectionMethod(*)) && args(param)")
  public void beforeSampleMethod2(Collection<?> param) {
    // Advice implementation
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public int getOrder() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
          UsageTracked usageTracked = (UsageTracked) context.getBean("myService");
  */
  /**
   * DOCUMENT ME!
   */
  @Pointcut("execution(* ver*(..))")
  public void aspectjLoadTimeWeavingExamples() {
  }

  /**
   * DOCUMENT ME!
   */
  @Pointcut("execution(* entender*(..))")
  public void aspectjLoadTimeWeavingExamples2() {
  }

  /**
   * DOCUMENT ME!
   *
   * @param pjp DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   *
   * @throws Throwable La excepción a enviar
   */
  @Around("aspectjLoadTimeWeavingExamples()")
  public Object profile(ProceedingJoinPoint pjp) throws Throwable {
    final Logger logger = LoggerFactory.getLogger(pjp.getSignature()
                                                     .getDeclaringType());

    logger.info("logAround() is running!");
    logger.info("hijacked method : " + pjp.getSignature().getName());
    logger.info("hijacked arguments : " + Arrays.toString(pjp.getArgs()));

    logger.info("Around before is running!");

    Object ret = pjp.proceed();
    logger.info("Around after is running!");

    logger.info("******");

    return ret;
  }

  /**
   * DOCUMENT ME!
   */
  @Before("execution(* fr.eni.beans.Barco.arrancarTurbina(..))")
  public void comprobarCombustibleTurbina() {
    final Logger logger = LoggerFactory.getLogger(VerificarAntes.class);
    logger.info("antes de arrancar la turbina");
  }

  /**
   * DOCUMENT ME!
   */
  @Before("execution(* arrancar*(..))")
  public void verificarCombustible() {
    System.out.println("justo antes de arrancar");
  }

  /**
   * DOCUMENT ME!
   */
  @After("execution(* arrancar*(..))")
  public void verificarVelocidad() {
    System.out.println("Después de arrancar!");
  }

  //Cambiar ejemplo
  /**
   * DOCUMENT ME!
   *
   * @param retVal DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  @After("execution(* arrancar*(..))")
  public Object verificarCombustible(Object retVal) {
    System.out.println("Justo despues de arrancar");

    return (retVal);
  }

  /**
   * DOCUMENT ME!
   *
   * @param joinPoint DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   *
   * @throws Throwable La excepción a enviar
   */
  @Around("execution(* arrancar*(..))")
  public Object generique(ProceedingJoinPoint joinPoint)
    throws Throwable {
    final Logger logger = LoggerFactory.getLogger(joinPoint.getSignature()
                                                           .getDeclaringType());

    logger.info("Intercepción arranque:");
    logger.info("metodo: " + joinPoint.getSignature().getName());
    logger.info("argumentos : " + Arrays.toString(joinPoint.getArgs()));

    logger.info("Around before is running!");

    Object ret = joinPoint.proceed();
    logger.info("Around after is running!");

    logger.info("Return " + ret.toString());

    return ret;
  }

  //Problema	
  @Aspect
  public class UsageTracking {
    //	    @DeclareParents(value="com.xzy.myapp.service.*+", defaultImpl=DefaultUsageTracked.class)
    //	    public static UsageTracked mixin;
    @Before("com.xyz.myapp.SystemArchitecture.businessService() && this(usageTracked)")
    public void recordUsage(UsageTracked usageTracked) {
      usageTracked.incrementUseCount();
    }
  }

  @Aspect
  public class AfterThrowingExample {
    @AfterThrowing("com.xyz.myapp.SystemArchitecture.dataAccessOperation()")
    public void doRecoveryActions() {
      // ...
    }
  }

  @Aspect
  public class AfterThrowingExample2 {
    @AfterThrowing(pointcut = "com.xyz.myapp.SystemArchitecture.dataAccessOperation()", throwing = "ex")
    public void doRecoveryActions(DataAccessException ex) {
      // ...
    }
  }

  @Aspect
  public class AfterFinallyExample {
    @After("com.xyz.myapp.SystemArchitecture.dataAccessOperation()")
    public void doReleaseLock() {
      // ...
    }
  }
}
