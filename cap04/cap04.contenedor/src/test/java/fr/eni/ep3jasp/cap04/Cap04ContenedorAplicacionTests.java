package fr.eni.ep3jasp.cap04;

import fr.eni.ep3jasp.cap04.services.MyService;
import fr.eni.ep3jasp.cap04.services.MyServiceLambdaDeclImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:applicationContext.xml",
    "classpath:applicationContext-test.xml"})
public class Cap04ContenedorApplicationTests {

  @Test
  public void test() {
    log.info("Capítulo 4");

    //GenericApplicationContext es necesario para poder cargar a través de un lambda
    //Si no, Generic es suficiente
    GenericApplicationContext genericApplicationContext = new GenericApplicationContext();
    XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(genericApplicationContext);
    xmlReader.loadBeanDefinitions(new ClassPathResource("applicationContext.xml"));
    genericApplicationContext.refresh();


    //Declarado en el archivo xml
    MyService myServiceByXml=(MyService)genericApplicationContext.getBean("myServiceByXml");
    myServiceByXml.myMethod("Declarado en el archivo xml");

    //Declarado a través de una annotation
    MyService myServiceByAnnotation=(MyService)genericApplicationContext.getBean("myServiceByAnnotation");
    myServiceByAnnotation.myMethod("Declarado a través de una anotación");

    //Declarado por @Configuration
    MyService myServiceByConfiguration=(MyService)genericApplicationContext.getBean("myServiceByConfiguration");
    myServiceByAnnotation.myMethod("Declarado a través de una clase @Configuration");

    //Declarado por una lambda
    genericApplicationContext.registerBean(MyServiceLambdaDeclImpl.class, () -> new MyServiceLambdaDeclImpl());
    MyServiceLambdaDeclImpl myServiceByLambda=(MyServiceLambdaDeclImpl)genericApplicationContext.getBean("fr.eni.ep3jasp.cap04.services.MyServiceLambdaDeclImpl");
    myServiceByLambda.myMethod("Declarado a través de una lambda");
  }
}
