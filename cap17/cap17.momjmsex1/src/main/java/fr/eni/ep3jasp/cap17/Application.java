
package fr.eni.ep3jasp.cap17;

import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@EnableJms
public class Application implements CommandLineRunner {
  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  @Bean
  CountDownLatch contador() {
    return new CountDownLatch(3);
  }

  @Autowired
  CountDownLatch contador;

  @Bean
  public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                  DefaultJmsListenerContainerFactoryConfigurer configurer) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    configurer.configure(factory, connectionFactory);
    return factory;
  }

  @Bean
  public MessageConverter jacksonJmsMessageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    return converter;
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
  }

  @Autowired
  JmsTemplate jmsTemplate;

  @Override
  public void run(String... args) throws Exception {
    LOGGER.info("Envío de un mensaje.");
    jmsTemplate.convertAndSend("mailbox", new Email("toto@titi.com", "Cuerpo del mensaje 1"));
    LOGGER.info("Envío de un mensaje.");
    jmsTemplate.convertAndSend("mailbox", new Email("tata@tutu.com", "Cuerpo del mensaje 2"));
    LOGGER.info("Envío de un mensaje.");
    jmsTemplate.convertAndSend("mailbox", new Email("tete@tyty.com", "Cuerpo del mensaje 3"));
    LOGGER.info("Espera el final del procesamiento...");
    contador.await();
    System.exit(0);
  }
}
