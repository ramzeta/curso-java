package fr.eni;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * Ejemplo Spring JDBC dificultad media
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class Media {
	public static void main(String[] args) {
		SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/test3");
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

        // Split up the array of whole names into an array of first/last names
		// Utilizamos los streams java 8 para preparar las líneas a inseratar
        List<Object[]> valoresFormateados = Arrays.asList("G g", "H h", "I i", "J j").stream()
                .map(enreg -> enreg.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        valoresFormateados.forEach(enreg -> log.info(String.format("Inserción de registros en la BDD : %s %s", enreg[0], enreg[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO usuario(nombre, apellido) VALUES (?,?)", valoresFormateados);

        log.info("Búsqueda de usuario nombre > 'H':");
        jdbcTemplate.query(
                "SELECT id, nombre, apellido FROM usuario WHERE nombre > ?", new Object[] { "H" },
                (rs, rowNum) -> new Usuario(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellido"))
        ).forEach(usuario -> log.info(usuario.toString()));

		//lo mismo que el ejemplo anterior  con un objeto Usuario, el uso de lambok para accesores
        //y java 8 para programar interfaces funcionales y lambdas lo que simplifica el código.	
        List<Map<String, Object>> lista = jdbcTemplate.queryForList("select * from usuario");
        log.info(lista.toString());
        
		jdbcTemplate.execute("delete from usuario");
		jdbcTemplate.execute("drop table usuario");

		dataSource.destroy();
	}

}
