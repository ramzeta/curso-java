package fr.eni;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

final class UsuarioMapper implements RowMapper<Usuario> {

    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Usuario usuario = new Usuario();
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        return usuario;
    }
}

/**
 * Ejemplo Spring JDBC práctico
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class Practico {
	public static void main(String[] args) {
		SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/testp");
		dataSource.setUsername("sa");
		dataSource.setPassword("");

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

		List<Usuario> usuarios= jdbcTemplate.query( "select nombre, apellido from usuario", new UsuarioMapper());
		    
		log.info("Usuarios:");
		usuarios.forEach(util -> log.info(util.toString()));

		
		jdbcTemplate.execute("delete from usuario");
		jdbcTemplate.execute("drop table usuario");

		dataSource.destroy();
	}

}
