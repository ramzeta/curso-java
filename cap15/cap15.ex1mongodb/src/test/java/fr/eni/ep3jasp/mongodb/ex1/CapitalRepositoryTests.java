package fr.eni.ep3jasp.mongodb.ex1;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CapitalRepositoryTests {
    @Autowired
    CapitalRepository repository;

    private Capital chili, chine, chypre;

    @Before
    public void setUp() {
        repository.deleteAll();
        chili = repository.save(new Capital("Chile", "Santiago","América"));
        chine = repository.save(new Capital("China","Pekín","Asia"));
        chypre = repository.save(new Capital("Chipre","Nicosia"," Europa"));
    }

    @Test
    public void setsIdOnSave() {
        Capital bresil = repository.save(new Capital("Brasil", "Brasilia", "América"));
        assertThat(bresil.getId()).isNotNull();
    }

    @Test
    public void findByContinente() {
        List<Capital> result = repository.findByContinente("América");
        Assertions.assertThat(result).hasSize(1).extracting("pais").contains("Chile");
    }

    @Test
    public void findsByExample() {
        Capital ejemplo = new Capital(null, null, "América");
        List<Capital> result = repository.findAll(Example.of(ejemplo));
        Assertions.assertThat(result).hasSize(1).extracting("pais").contains("Chile");
    }
}