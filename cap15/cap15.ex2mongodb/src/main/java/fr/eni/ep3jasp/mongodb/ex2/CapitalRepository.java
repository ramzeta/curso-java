package fr.eni.ep3jasp.mongodb.ex2;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// tag::repository[]
@RepositoryRestResource(collectionResourceRel = "capital", path = "capitales")
public interface CapitalRepository extends MongoRepository<Capital, String> {
    Capital findPais(@Param("pais") String pais);
    Capital findByCapital(@Param("capital") String capital);
    List<Capital> findByContinente(@Param("continente") String continente);
}
// end::repository[]
