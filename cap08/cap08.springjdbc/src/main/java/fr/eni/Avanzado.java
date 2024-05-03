package fr.eni;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Utilización más compleja de Spring JDBC
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class Avanzado {
  /**
   * DOCUMENT ME!
   */
  public static ClassPathXmlApplicationContext appContext;

  /**
   * DOCUMENT ME!
   */
  public static BeanFactory factory;

  /**
   * DOCUMENT ME!
   */
  public static void init() {
    appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    factory = (BeanFactory) appContext;

    BasicDataSource dataSource = (BasicDataSource) factory.getBean("dataSource");

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.execute("drop TABLE IF EXISTS usuario");
    jdbcTemplate.execute(
      "create table usuario (id serial, nombre varchar, apellido varchar)");

    for (int i = 0; i < 500; i++) {
      jdbcTemplate.execute("insert into usuario (nombre, apellido) values ('A" +
        i + "', 'a" + i + "')");
    }
  }

  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   * 
   * @throws SQLException La excepción sql enviada
   */
  public static void main(String[] args) throws SQLException {
    init();

    AvanzadoDao dao = (AvanzadoDao) factory.getBean("avanzadoDao");

    List<Usuario> us = new ArrayList<Usuario>();

    for (int i = 0; i < 500; i++) {
      us.add(new Usuario(i, "Apellido" + i, "Nombre" + i));
    }

    log.info("classic JDBC...");

    int[][] res = dao.batchMayPor100(us);

    int numlotes = res.length;
    log.info("Num lotes:" + numlotes);

    us = dao.get5Apellido();
    log.info(us.toString());
  }
}
