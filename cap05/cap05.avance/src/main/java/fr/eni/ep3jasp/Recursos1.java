package fr.eni.ep3jasp;

import fr.eni.ep3jasp.services.ResourceLoaderAndPrinter;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Los archivos de recursos estándares
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class Recursos1 {

  /**
   * Punto de entrada
   *
   * @param args Argumentos de la línea de comandos
   *
   * @throws IOException Excepción generada
   */
  public static void main(String[] args) throws IOException {
    ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {
        "applicationContext.xml"
    });

    ResourceLoaderAndPrinter resourceLoaderAndPrinter=
        (ResourceLoaderAndPrinter)ctx.getBean("loaderprinter");

    String archivo = System.getProperty("user.dir") +
            "/cap05/cap05.avance/src/main/resources/fr/eni/ep3jasp/recurso.txt";
    log.info("Archivo:" + archivo);

    Resource r0 = new FileSystemResource(archivo);
    resourceLoaderAndPrinter.tratarRecurso(r0,"FileSystemResource");

    Resource r1 = new UrlResource("file:" + archivo);
    resourceLoaderAndPrinter.tratarRecurso(r1,"UrlResource");

    Resource r2 = new ClassPathResource("fr/eni/ep3jasp/resource.txt");
    resourceLoaderAndPrinter.tratarRecurso(r2,"ClassPathResource");
  }
}
