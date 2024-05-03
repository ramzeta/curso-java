package fr.eni.ep3jasp.cap17;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

// tag::code[]
@Component
public class Receiver {
  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch;

  //Constructor
  public Receiver(CountDownLatch latch) {
    this.latch = latch;
  }

  @JmsListener(destination = "mailbox", containerFactory = "myFactory")
  public void receiveMessage(Email email) {
    LOGGER.info("Recepción[{}]",email);

    latch.countDown();
  }
}
// end::code[]