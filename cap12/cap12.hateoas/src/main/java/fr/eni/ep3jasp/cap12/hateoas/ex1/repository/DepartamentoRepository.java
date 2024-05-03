package fr.eni.ep3jasp.cap12.hateoas.ex1.repository;

import fr.eni.ep3jasp.cap12.hateoas.ex1.domain.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource(collectionResourceRel = "departamentos", path = "departamentos")
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}

