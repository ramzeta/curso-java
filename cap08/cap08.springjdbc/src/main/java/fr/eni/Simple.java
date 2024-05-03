package fr.eni;

import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * Ejemplo Spring JDBC simple
 *
 * @author $author$
 * @version $Revision$
  */
public class Simple {
	public static void main(String[] args) {
		SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/testsimple");
		dataSource.setUsername("sa");
		dataSource.setPassword("");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("drop TABLE IF EXISTS usuario");
		jdbcTemplate.execute("create table usuario (id int, nombre varchar, apellido varchar)");
		jdbcTemplate.execute("insert into usuario (id, nombre, apellido) values (1, 'A', 'a')");
		jdbcTemplate.execute("insert into usuario (id, nombre, apellido) values (2, 'B', 'b')");
		jdbcTemplate.execute("insert into usuario (id, nombre, apellido) values (3, 'C', 'c')");
		jdbcTemplate.execute("insert into usuario (id, nombre, apellido) values (4, 'D', 'd')");
		jdbcTemplate.execute("insert into usuario (id, nombre, apellido) values (5, 'E', 'e')");
		jdbcTemplate.execute("insert into usuario (id, nombre, apellido) values (6, 'F', 'f')");
		// Requete simple
		int count = jdbcTemplate.queryForObject("select count(*) from usuario", Integer.class);

		System.out.println(count);

		// Agregación
		Object[] parametros = new Object[] { "C" };
		count = jdbcTemplate.queryForObject("select count(*) from usuario where nombre > ?", Integer.class, parametros);
		
		jdbcTemplate.update("DELETE FROM usuario WHERE id = ?", new Object[] { "4" });

		count = jdbcTemplate.queryForObject("select count(*) from usuario", Integer.class);
		System.out.println(count);

	    jdbcTemplate.batchUpdate(new String[] { 
	    		"update usuario set nombre = 'AAA' where id = 3",
                "delete from usuario where id = 5" });
	    
	    jdbcTemplate.update("update usuario set nombre=? where id=?",	    		
	            new Object[] { "Z", 1L }, new int[] { Types.VARCHAR, Types.INTEGER });
	    
	    
	    parametros = new Object[] { new Integer(1) };
	    SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select id, nombre, apellido from usuario where id > ?", parametros);
	    int rowCount = 0;
	    while (sqlRowSet.next()) {
	      System.out.println(sqlRowSet.getString("id") + " - " + sqlRowSet.getString("nombre")+ " - " + sqlRowSet.getString("apellido"));
	      rowCount++;
	    }
	    System.out.println(rowCount);

	    
		
		jdbcTemplate.execute("delete from usuario");
		jdbcTemplate.execute("drop table usuario");

		dataSource.destroy();
	}

}
