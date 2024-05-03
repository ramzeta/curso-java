package fr.eni.ep3jasp.mongodb.ex1;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

// tag::repository[]
public interface CapitalRepository extends MongoRepository<Capital, String> {
    Capital findPais(String pais);
    Capital findByCapital(String capital);
    List<Capital> findByContinente(String continente);
}
// end::repository[]