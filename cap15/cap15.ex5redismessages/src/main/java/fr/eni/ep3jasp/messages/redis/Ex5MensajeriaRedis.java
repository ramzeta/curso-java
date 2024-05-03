package fr.eni.ep3jasp.messages.redis;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SpringBootApplication
public class Ex5MensajeriaRedis implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(Ex5MensajeriaRedis.class);

  public static void main(String[] args) {
    SpringApplication.run(Ex5MensajeriaRedis.class, args);
  }

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  @Autowired
  CountDownLatch contador;

  @Override
  public void run(String... args) throws Exception {
    LOGGER.info("Sending message...");
    stringRedisTemplate.convertAndSend("mensajeria", "Arranca la aplicación");
    stringRedisTemplate.convertAndSend("mensajeria", "Ejecución de los tratamientos");
    stringRedisTemplate.convertAndSend("mensajeria", "Fin de os tratamientos");
    contador.await();
    System.exit(0);
  }


  @Bean
  StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
    return new StringRedisTemplate(connectionFactory);
  }

  @Bean
  MessageListenerAdapter listenerAdapter(ReceptorMessages receptorMessages) {
    return new MessageListenerAdapter(receptorMessages, "receiveMessage");
  }

  @Bean
  RedisMessageListenerContainer conteneur(RedisConnectionFactory connectionFactory,
                                          MessageListenerAdapter listenerAdapter) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(listenerAdapter, new PatternTopic("mensajeria"));
    return container;
  }

  @Bean
  ReceptorMessages receptor(CountDownLatch contador) {
    return new ReceptorMessages(contador);
  }

  @Bean
  CountDownLatch contador() {
    return new CountDownLatch(3);
  }

  class ReceptorMessages {
    private final Logger LOGGER = LoggerFactory.getLogger(ReceptorMessages.class);

    private CountDownLatch latch;

    @Autowired
    public ReceptorMessages(CountDownLatch latch) {
      this.latch = latch;
    }

    public void receiveMessage(String message) {
      LOGGER.info("Recepción del mensaje [{}]",message);
      latch.countDown();
    }
  }
}
