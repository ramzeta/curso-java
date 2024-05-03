package fr.eni;

import java.util.List;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Ejemplo Spring JDBC config XML
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class ConfigXml2 {

	public static void init() {
		BeanFactory factory = (BeanFactory) appContext;
		BasicDataSource dataSource = (BasicDataSource) factory
				.getBean("dataSource");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("drop TABLE IF EXISTS usuario");
		jdbcTemplate
				.execute("create table usuario (id int, nombre varchar, apellido varchar)");
		jdbcTemplate
				.execute("insert into usuario (id, nombre, apellido) values (1, 'A', 'a')");
		jdbcTemplate
				.execute("insert into usuario (id, nombre, apellido) values (2, 'B', 'b')");
		jdbcTemplate
				.execute("insert into usuario (id, nombre, apellido) values (3, 'C', 'c')");
		jdbcTemplate
				.execute("insert into usuario (id, nombre, apellido) values (4, 'D', 'd')");
		jdbcTemplate
				.execute("insert into usuario (id, nombre, apellido) values (5, 'E', 'e')");
		jdbcTemplate
				.execute("insert into usuario (id, nombre, apellido) values (6, 'F', 'f')");
	}

	public static ClassPathXmlApplicationContext appContext;

	public static void main(String[] args) {

		appContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		// of course, an ApplicationContext is just a BeanFactory
		BeanFactory factory = (BeanFactory) appContext;
		init();

		JdbcUsuarioDao dao = (JdbcUsuarioDao) factory
				.getBean("jdbcUsuarioDao");
		List<Usuario> usuarios = dao.getAll();

		usuarios.forEach(util -> log.info(util.toString()));

	}
}
