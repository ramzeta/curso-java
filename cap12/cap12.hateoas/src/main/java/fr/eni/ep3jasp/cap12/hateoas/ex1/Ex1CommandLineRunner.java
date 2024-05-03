package fr.eni.ep3jasp.cap12.hateoas.ex1;

import fr.eni.ep3jasp.cap12.hateoas.ex1.domain.Departamento;
import fr.eni.ep3jasp.cap12.hateoas.ex1.domain.Region;
import fr.eni.ep3jasp.cap12.hateoas.ex1.repository.DepartamentoRepository;
import fr.eni.ep3jasp.cap12.hateoas.ex1.repository.RegionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// tag::code[]
@Component
public class Ex1CommandLineRunner implements org.springframework.boot.CommandLineRunner {
  private static final Logger LOGGER = LoggerFactory.getLogger(Ex1CommandLineRunner.class);

  @Autowired
  private RegionRepository regionRepository;
  @Autowired
  private DepartamentoRepository departamentoRepository;

	@Override
  public void run(String... strings) throws Exception {
    Region r53=new Region().code("53").nombre("Cantábrico");
    Departamento d22=new Departamento().code("22").nombre("Santander").region(r53);
    Departamento d29=new Departamento().code("29").nombre("Bilbao").region(r53);
    Departamento d35=new Departamento().code("35").nombre("Oviedo").region(r53);
    Departamento d56=new Departamento().code("56").nombre("San Sebastian").region(r53);

    Region r52=new Region().code("52").nombre("Mediterraneo");
    Departamento d44=new Departamento().code("44").nombre("Huelva").region(r52);
    Departamento d49=new Departamento().code("49").nombre("Valencia").region(r52);
    Departamento d53=new Departamento().code("53").nombre("Tarragona").region(r52);
    Departamento d72=new Departamento().code("72").nombre("Murcia").region(r52);
    Departamento d85=new Departamento().code("85").nombre("Castellón").region(r52);

    regionRepository.save(r53);
    regionRepository.save(r52);
    departamentoRepository.save(d22);
    departamentoRepository.save(d29);
    departamentoRepository.save(d35);
    departamentoRepository.save(d56);
    departamentoRepository.save(d44);
    departamentoRepository.save(d49);
    departamentoRepository.save(d53);
    departamentoRepository.save(d72);
    departamentoRepository.save(d85);

    regionRepository.findAll().forEach(System.out::println);
    departamentoRepository.findAll().forEach(System.out::println);
  }

}
// end::code[]