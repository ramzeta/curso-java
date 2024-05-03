package fr.eni.ep3jasp.mongodb.ex1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// tag::starter[]
@SpringBootApplication
public class Ex1MongoDB implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(Ex1MongoDB.class);

  @Autowired
  private CapitalRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(Ex1MongoDB.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    repository.deleteAll();
    repository.save(new Capital("Afganistán", "Kabul", "Asia"));
    repository.save(new Capital("Sudafrica", "Pretoria", "África"));
    repository.save(new Capital("Albania", "Tirana", "Europa"));
    repository.save(new Capital("Algeria", "Alger", "África"));
    repository.save(new Capital("Alemania", "Berlín", "Europa"));
    repository.save(new Capital("Andorra", "Andorra-la-Vella", "Europa"));
    repository.save(new Capital("Angola", "Luanda", "África"));
    repository.save(new Capital("Antigua-y-Barbuda", "San John's", "América"));
    repository.save(new Capital("Arabia Saudí", "Riyad", "Asia"));
    repository.save(new Capital("Argentina", "Buenos Aires", "América"));
    repository.save(new Capital("Armenia", "Erevan", "Asia"));
    repository.save(new Capital("Australia", "Canberra", "Oceanía"));
    repository.save(new Capital("Autriche", "Viena", "Europa"));
    repository.save(new Capital("Azerbaijan", "Bakou", "Asia"));

    // Búsqueda de todas las capitales
    LOGGER.info("Todas las capitales con el uso de findAll():");
    for (Capital capital : repository.findAll()) {
      LOGGER.info("Capital:{}", capital);
    }

    // Buscar capital por pais
    LOGGER.info("Capital encontrada con findPais('Alemania'):");
    LOGGER.info("Alemania:{}", repository.findPais("Alemania"));

    LOGGER.info("Capital encontrada con findByCapital('Canberra'):");
    LOGGER.info("Canberra:{}", repository.findByCapital("Canberra"));

    LOGGER.info("Todas las capitales de Asia con findByContinente('Asia'):");
    for (Capital capital : repository.findByContinente("Asia")) {
      LOGGER.info("Capital:{}", capital);

      capital.getId();
    }
  }
}
// end::starter[]