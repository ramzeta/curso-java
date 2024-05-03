package fr.eni.ep3jasp.cap12.hateoas.ex1.repository;

import fr.eni.ep3jasp.cap12.hateoas.ex1.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// tag::code[]
@RepositoryRestResource(collectionResourceRel = "regiones", path = "regiones")
public interface RegionRepository extends JpaRepository<Region, Long> {
}
// end::code[]
