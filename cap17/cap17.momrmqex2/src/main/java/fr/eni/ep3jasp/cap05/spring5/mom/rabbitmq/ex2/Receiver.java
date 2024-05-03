package fr.eni.ep3jasp.cap05.spring5.mom.rabbitmq.ex2;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// tag::code[]
@Component
public class Receiver {
  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(3);

  public void receiveMessage(String email) {
    LOGGER.info("Recepción[{}]",email);
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}
// end::code[]