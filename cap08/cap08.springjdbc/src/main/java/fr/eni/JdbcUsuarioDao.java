package fr.eni;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Slf4j
public class JdbcUsuarioDao {

	public JdbcUsuarioDao() {
		super();
	}

	public void setDataSource(BasicDataSource dataSource) {
		log.info("por setter setDataSource");
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// Inyectado
	public JdbcUsuarioDao(BasicDataSource dataSource) {
		super();
		log.info("por constructor JdbcUsuarioDao");
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private BasicDataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public List<Usuario> getAll() {
		List<Usuario> usuarios = jdbcTemplate.query(
				"select nombre, apellido from usuario",
				new RowMapper<Usuario>() {
					public Usuario mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Usuario usuario = new Usuario();
						usuario.setNombre(rs.getString("nombre"));
						usuario.setApellido(rs.getString("apellido"));
						return usuario;
					}
				});

		return usuarios;
	}

}
