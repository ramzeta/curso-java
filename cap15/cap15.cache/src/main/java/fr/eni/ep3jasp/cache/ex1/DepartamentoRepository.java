package fr.eni.ep3jasp.cache.ex1;

// tag::code[]
@org.springframework.stereotype.Repository
public interface DepartamentoRepository {
    Departamento getByCode(String code);
}
// end::code[]
