package fr.eni.ep3jasp.angular.backend.controllers;

import fr.eni.ep3jasp.angular.backend.domain.Cuenta;
import fr.eni.ep3jasp.angular.backend.repositories.CuentaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CuentaController {
  private CuentaRepository repository;

  public CuentaController(CuentaRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/lascuentas")
  @CrossOrigin(origins = "http://localhost:4200")
  public List<Cuenta> getCuentas() {

    return repository.findAll();
  }
}