package fr.eni.ep3jasp;

import fr.eni.ep3jasp.services.ResourceLoaderAndPrinter;
import fr.eni.ep3jasp.services.ResourceHelperService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.core.io.Resource;


/**
 * Los archivos de recursos cargados a través 
 * de un servicio ResourceLoaderAware
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class Recursos3 {
  /**
   * Punto de entrada
   *
   * @param args Argumentos de la línea de comandos
   */
  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {
          "applicationContext.xml"
        });

    ResourceLoaderAndPrinter resourceLoaderAndPrinter=
        (ResourceLoaderAndPrinter)ctx.getBean("loaderprinter");

    ResourceHelperService svc = (ResourceHelperService) ctx.getBean(
        "resourceHelperService");

    String rep = System.getProperty("user.dir");
    log.info("Rep actual:" + rep);

    Resource r0 = svc.getResource("file:" + rep +
            "/cap05/cap05.avance/src/main/resources/fr/eni/ep3jasp/recurso.txt");
    resourceLoaderAndPrinter.tratarRecurso(r0, "getResource con file:");

    Resource r1 = svc.getResource("classpath:fr/eni/ep3jasp/resource.txt");
    resourceLoaderAndPrinter.tratarRecurso(r1, "getResource con classpath:");

    Resource r2 = svc.getResource(
        "url:http://echo.jsontest.com/key/value/one/two");
    resourceLoaderAndPrinter.tratarRecurso(r2, "getResource con url:");
  }
}
