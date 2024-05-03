package fr.eni.ep3jasp.cap04;

import fr.eni.ep3jasp.cap04.services.MyService;
import fr.eni.ep3jasp.cap04.services.MyServiceLambdaDeclImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class Cap04ContenedorApplication {
	public static void main(String[] args) {
		log.info("Capítulo 4");

		GenericApplicationContext genericApplicationContext = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(genericApplicationContext);
		xmlReader.loadBeanDefinitions(new ClassPathResource("applicationContext.xml"));
		genericApplicationContext.refresh();


		//Declarado en el archivo xml
		MyService myServiceByXml=(MyService)genericApplicationContext.getBean("myServiceByXml");
		myServiceByXml.myMethod("Declarado en el archivo xml");

		//Declarado en una anotación
		MyService myServiceByAnnotation=(MyService)genericApplicationContext.getBean("myServiceByAnnotation");
		myServiceByAnnotation.myMethod("Declarado a través de una anotación");

		//Declarado pr @Configuration
		MyService myServiceByConfiguration=(MyService)genericApplicationContext.getBean("myServiceByConfiguration");
		myServiceByAnnotation.myMethod("Declarado a través de una clase @Configuration");

		//Declarado por una lambda
		genericApplicationContext.registerBean(MyServiceLambdaDeclImpl.class, () -> new MyServiceLambdaDeclImpl());
		MyServiceLambdaDeclImpl myServiceByLambda=(MyServiceLambdaDeclImpl)genericApplicationContext.getBean("fr.eni.ep3jasp.cap04.services.MyServiceLambdaDeclImpl");
		myServiceByLambda.myMethod("Declarado a través de una lambda");

		String[] beanNames = genericApplicationContext.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			log.info("Bean : {}:{} ",beanName, genericApplicationContext.getBean(beanName).getClass().toString());
		}
	}
}
