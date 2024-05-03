package fr.eni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * Utilización menos sencilla de Spring JDBC
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class Complejo {
	public static void main(String[] args) throws SQLException {
		ClassPathXmlApplicationContext appContext;;
		
		appContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		
		BeanFactory factory = (BeanFactory) appContext;
		BasicDataSource dataSource = (BasicDataSource) factory
				.getBean("dataSource");
		ComplejoDao dao = (ComplejoDao) factory
				.getBean("complejoDao");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("drop TABLE IF EXISTS usuario");
		jdbcTemplate
				.execute("create table usuario (id serial, nombre varchar, apellido varchar)");
		jdbcTemplate
				.execute("insert into usuario (nombre, apellido) values ('A', 'a')");
		jdbcTemplate
				.execute("insert into usuario (nombre, apellido) values ('B', 'b')");
		jdbcTemplate
				.execute("insert into usuario (nombre, apellido) values ('C', 'c')");
		jdbcTemplate
				.execute("insert into usuario (nombre, apellido) values ('D', 'd')");
		jdbcTemplate
				.execute("insert into usuario (nombre, apellido) values ('E', 'e')");
		jdbcTemplate
				.execute("insert into usuario (nombre, apellido) values ('F', 'f')");

		final Usuario modelo=new Usuario(1,"a","A");
		
		Usuario usuario=dao.batchBuscarPorModelo(modelo);
		List<Usuario> usuarios =dao.batchBuscarTodoPorModelo(modelo);
		
		log.info("Usuarios:");
		usuarios.forEach(util -> log.info(util.toString()));

		final String INSERT_SQL = "insert into usuario (nombre) values(?)";
		final String name = "Yo";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {"id"});
		            ps.setString(1, name);
		            return ps;
		        }
		    },
		    keyHolder);

		log.info("clave="+keyHolder.getKey());

		List<Usuario> us=new ArrayList<Usuario>();
		us.add(new Usuario(1,"z","Z"));
		us.add(new Usuario(2,"y","Y"));
		us.add(new Usuario(3,"x","X"));
		
		log.info("classic JDBC...");
		int[] res= dao.batchMay0(us);
		String codes = Arrays.stream(res)
			    .mapToObj(Integer::toString)
			    .collect(Collectors.joining(",","[","]"));
		
		log.info("códigos="+codes);
		
		List<Usuario> us3 = dao.getAll();
		log.info("Después modif:"+us3.toString());
		
		us=new ArrayList<Usuario>();
		us.add(new Usuario(1,"ffff","FFFF"));
		us.add(new Usuario(2,"gggg","GGGG"));
		
		log.info("BatchPreparedStatementSetter...");
		res= dao.batchMay1(us);
		
		codes = Arrays.stream(res)
			    .mapToObj(Integer::toString)
			    .collect(Collectors.joining(",","[","]"));
		
		log.info("códigos="+codes);
		
		us3 = dao.getAll();
		log.info("Después modif:"+us3.toString());
		
		us=new ArrayList<Usuario>();
		us.add(new Usuario(1,"rrrr","RRRR"));
		us.add(new Usuario(2,"tttt","TTTT"));
		
		log.info("SqlParameterSource...");
		res= dao.batchMay2(us);
		
		codes = Arrays.stream(res)
			    .mapToObj(Integer::toString)
			    .collect(Collectors.joining(",","[","]"));
		
		log.info("códigos="+codes);
		
		us3 = dao.getAll();
		log.info("Después modif:"+us3.toString());
		
	}

}
