package fr.eni;

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
public class ConfigXml {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		// of course, an ApplicationContext is just a BeanFactory
		BeanFactory factory = (BeanFactory) appContext;
		
		BasicDataSource dataSource = (BasicDataSource) factory.getBean("dataSource");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("drop TABLE IF EXISTS usuario");
		jdbcTemplate.execute("create table usuario (id int, nombre varchar, apellido varchar)");
		jdbcTemplate.execute("insert into usuario (id, nombre, apellido) values (1, 'A', 'a')");

		jdbcTemplate.execute("delete from usuario");
		jdbcTemplate.execute("drop table usuario");
	}
}
