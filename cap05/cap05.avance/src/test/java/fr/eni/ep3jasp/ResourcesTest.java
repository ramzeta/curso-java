package fr.eni.ep3jasp;

import fr.eni.ep3jasp.services.ResourceLoaderAndPrinter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:applicationContext.xml",
    "classpath:applicationContext-test.xml"})
public class ResourcesTest {

  @Autowired
  ApplicationContext context;

  @Autowired
  ResourceLoaderAndPrinter resourceLoaderAndPrinter;

  @Test
  public void ResTest() throws MalformedURLException {
    String archivo = System.getProperty("user.dir") +
        "/src/main/resources/fr/eni/recurso.txt";
    log.info("Archivo:" + archivo);

    Resource r0 = new FileSystemResource(archivo);
    resourceLoaderAndPrinter.tratarRecurso(r0, "FileSystemResource");

    Resource r1 = new UrlResource("file:" + archivo);
    resourceLoaderAndPrinter.tratarRecurso(r1, "UrlResource");

    Resource r2 = new ClassPathResource("fr/eni/ep3jasp/recurso.txt");
    resourceLoaderAndPrinter.tratarRecurso(r2, "ClassPathResource");
  }

  @Test
  public void ResTest2() throws MalformedURLException {
    String rep = System.getProperty("user.dir");
    log.info("Rep actual:" + rep);

    Resource r0 = context.getResource("file:" + rep +
        "/src/main/resources/fr/eni/recurso.txt");
    resourceLoaderAndPrinter.tratarRecurso(r0, "getResource con file:");

    Resource r1 = context.getResource("classpath:fr/eni/recurso.txt");
    resourceLoaderAndPrinter.tratarRecurso(r1, "getResource con classpath:");

    Resource r2 = context.getResource(
        "url:http://echo.jsontest.com/key/value/one/two");
    resourceLoaderAndPrinter.tratarRecurso(r2, "getResource con url:");
  }

  ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{
      "applicationContext.xml"
  });

  @Test
  public void ResTest3() throws MalformedURLException {

    String rep = System.getProperty("user.dir");
    log.info("Rep actual:" + rep);

    Resource r0 = context.getResource("file:" + rep +
        "/src/main/resources/fr/eni/recurso.txt");
    resourceLoaderAndPrinter.tratarRecurso(r0, "getResource con file:");

    Resource r1 = context.getResource("classpath:fr/eni/resource.txt");
    resourceLoaderAndPrinter.tratarRecurso(r1, "getResource con classpath:");

    Resource r2 = context.getResource(
        "url:http://echo.jsontest.com/key/value/one/two");
    resourceLoaderAndPrinter.tratarRecurso(r2, "getResource con url:");
  }
}
