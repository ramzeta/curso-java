package fr.eni.ep3jasp;

import fr.eni.ep3jasp.cache.ex3.SimpleDepartamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Ex3CacheRedis implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(Ex3CacheRedis.class);

  public static void main(String[] args) {
    SpringApplication.run(Ex3CacheRedis.class, args);
  }

  @Autowired
  private SimpleDepartamentoRepository departamentoRepository;

  @Override
  public void run(String... args) throws Exception {
    departamentoRepository.cacheEvict();

    LOGGER.info("27->{}", departamentoRepository.getByCode("27"));
    LOGGER.info("44->{}", departamentoRepository.getByCode("44"));
    LOGGER.info("51->{}", departamentoRepository.getByCode("51"));

    LOGGER.info("27->{}", departamentoRepository.getByCode("27"));
    LOGGER.info("44->{}", departamentoRepository.getByCode("44"));
    LOGGER.info("51->{}", departamentoRepository.getByCode("51"));

    departamentoRepository.patch("27","__GALICIA__");
    LOGGER.info("27->{}", departamentoRepository.getByCode("27"));

  }
}
