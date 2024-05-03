package fr.eni.ep3jasp.angular.backend;

import fr.eni.ep3jasp.angular.backend.repositories.CuentaRepository;
import fr.eni.ep3jasp.angular.backend.domain.Cuenta;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CuentaCommandLineRunner implements CommandLineRunner {

    private final CuentaRepository repository;

    public CuentaCommandLineRunner(CuentaRepository repository) {
      this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
      repository.save(new Cuenta(2741, "Préstamos de capital"));
      repository.save(new Cuenta(2742, "Préstamos asociados"));
      repository.save(new Cuenta(2743, "Préstamos personales"));
      repository.findAll().forEach(System.out::println);
    }
}
