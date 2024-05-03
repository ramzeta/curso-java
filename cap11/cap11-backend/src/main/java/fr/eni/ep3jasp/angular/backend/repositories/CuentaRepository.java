package fr.eni.ep3jasp.angular.backend.repositories;

import fr.eni.ep3jasp.angular.backend.domain.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
