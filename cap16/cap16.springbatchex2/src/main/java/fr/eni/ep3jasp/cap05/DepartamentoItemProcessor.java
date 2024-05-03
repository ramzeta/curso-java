package fr.eni.ep3jasp.cap05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class DepartamentoItemProcessor implements ItemProcessor<Departamento, Departamento> {

  private static final Logger log = LoggerFactory.getLogger(DepartamentoItemProcessor.class);

  @Override
  public Departamento process(final Departamento departamento) throws Exception {
    final String code= departamento.getCode().toUpperCase();
    final String nombre = departamento.getNombre().toUpperCase();
    final Departamento transformedDepartamento = new Departamento(code, nombre);
    log.info("Converting (" + departamento + ") into (" + transformedDepartamento + ")");

    return transformedDepartamento;
  }
}
