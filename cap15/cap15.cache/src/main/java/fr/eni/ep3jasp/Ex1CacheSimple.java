package fr.eni.ep3jasp;

import fr.eni.ep3jasp.cache.ex1.DepartamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Ex1CacheSimple implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(Ex1CacheSimple.class);

  public static void main(String[] args) {
    SpringApplication.run(Ex1CacheSimple.class, args);
  }

  @Autowired
  private DepartamentoRepository departamentoRepository;

  @Override
  public void run(String... args) throws Exception {
    LOGGER.info("27->{}", departamentoRepository.getByCode("27"));
    LOGGER.info("44->{}", departamentoRepository.getByCode("44"));
    LOGGER.info("51->{}", departamentoRepository.getByCode("51"));

    LOGGER.info("27->{}", departamentoRepository.getByCode("27"));
    LOGGER.info("44->{}", departamentoRepository.getByCode("44"));
    LOGGER.info("51->{}", departamentoRepository.getByCode("51"));
  }
}
// end::starter[]