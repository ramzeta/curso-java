package fr.eni;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


/**
 * Ejemplo Spring JDBC con estilo map
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class EstiloMapa {
  /**
   * DOCUMENT ME!
   */
  public static ClassPathXmlApplicationContext appContext;

  /**
   * DOCUMENT ME!
   */
  public static void init() {
    BeanFactory factory = (BeanFactory) appContext;
    BasicDataSource dataSource = (BasicDataSource) factory.getBean("dataSource");
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.execute("drop TABLE IF EXISTS usuario");
    jdbcTemplate.execute(
      "create table usuario (id int, nombre varchar, apellido varchar)");
    jdbcTemplate.execute(
      "insert into usuario (id, nombre, apellido) values (1, 'A', 'a')");
    jdbcTemplate.execute(
      "insert into usuario (id, nombre, apellido) values (2, 'B', 'b')");
    jdbcTemplate.execute(
      "insert into usuario (id, nombre, apellido) values (3, 'B', 'c')");
    jdbcTemplate.execute(
      "insert into usuario (id, nombre, apellido) values (4, 'B', 'd')");
    jdbcTemplate.execute(
      "insert into usuario (id, nombre, apellido) values (5, 'E', 'e')");
    jdbcTemplate.execute(
      "insert into usuario (id, nombre, apellido) values (6, 'F', 'f')");
  }

  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    BeanFactory factory = (BeanFactory) appContext;
    init();

    ParametroUsuarioDao dao = (ParametroUsuarioDao) factory.getBean(
        "parametroUsuarioDao");

    int numeroPorNombre = dao.contarPorNombre("B");
    log.info("Nombre=" + numeroPorNombre);

    int numeroPorEjemplo = dao.contarPorEjemplo(new Usuario(3, "b", "B"));
    log.info("numeroPorEjemplo=" + numeroPorEjemplo);
  }
}
