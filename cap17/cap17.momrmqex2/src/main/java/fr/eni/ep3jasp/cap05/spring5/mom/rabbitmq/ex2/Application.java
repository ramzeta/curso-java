package fr.eni.ep3jasp.cap05.spring5.mom.rabbitmq.ex2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

// tag::code[]
@SpringBootApplication
public class Application implements CommandLineRunner {
  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  final static String queueName = "spring-boot";

  @Bean
  Queue queue() {
    return new Queue(queueName, false);
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange("spring-boot-exchange");
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(queueName);
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                           MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(Application.class, args);
  }

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private Receiver receiver;

  @Autowired
  private ConfigurableApplicationContext context;

  @Override
  public void run(String... args) throws Exception {
    LOGGER.info("Envío de un mensaje.");
    rabbitTemplate.convertAndSend(Application.queueName, "Mensaje número 1");

    LOGGER.info("Envío de un mensaje.");
    rabbitTemplate.convertAndSend(Application.queueName, "Mensaje número 2");

    LOGGER.info("Envío de un mensaje.");
    rabbitTemplate.convertAndSend(Application.queueName, "Mensaje número 3");

    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    context.close();
  }
}
// code::code[]