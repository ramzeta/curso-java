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
 * Ejemplo simple Spring JDBC
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class Simplificado {
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

    for (int i = 1; i < 3; i++) {
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

    JdbcUsuarioDao dao = (JdbcUsuarioDao) factory.getBean(
        "jdbcUsuarioDao");

    List<Usuario> us = dao.getAll();
    log.info(us.toString());

    Usuario u = new Usuario(1, "aaaa", "AAAA");
    dao.agregar(u);
    log.info("Usuario agregado=" + u.toString() + " id no modificado");

    us = dao.getAll();
    log.info(us.toString());

    dao.agregar2(u);
    log.info("Usuario agregado=" + u.toString() + " id modificado");

    dao.agregar3(u);
    log.info("Usuario agregado=" + u.toString() + " id modificado");

    dao.agregar4(u);
    log.info("Usuario agregado=" + u.toString() + " id modificado");


  }
}
