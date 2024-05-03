package fr.eni;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ComplejoDao {

	private BasicDataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(BasicDataSource dataSource) {
		log.info("por setter setDataSource");
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);


	}
	public List<Usuario> getAll() {
		List<Usuario> usuarios = jdbcTemplate.query(
				"select nombre, apellido, id from usuario",
				new RowMapper<Usuario>() {
					public Usuario mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Usuario usuario = new Usuario();
						usuario.setNombre(rs.getString("nombre"));
						usuario.setApellido(rs.getString("apellido"));
						usuario.setId(rs.getInt("id"));
						return usuario;
					}
				});

		return usuarios;
	}
	public Usuario batchBuscarPorModelo(final Usuario usuario) {
		Usuario u2 = jdbcTemplate.queryForObject(
				"select nombre, apellido from usuario where id = ?",
				new Object[] { 5 }, new RowMapper<Usuario>() {
					public Usuario mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Usuario usuario = new Usuario();
						usuario.setNombre(rs.getString("nombre"));
						usuario.setApellido(rs.getString("apellido"));
						return usuario;
					}
				});
		System.out.println(u2.toString());
		return u2;
	}
	public List<Usuario> batchBuscarTodoPorModelo(final Usuario usuario) {
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
	public int[] batchMay0(final List<Usuario> usuarios) {
        List<Object[]> batch = new ArrayList<Object[]>();
        for (Usuario actor : usuarios) {
            Object[] values = new Object[] {
                    actor.getNombre(),
                    actor.getApellido(),
                    actor.getId()};
            batch.add(values);
        }
        int[] updateCounts = jdbcTemplate.batchUpdate(
                "update usuario set nombre = ?, apellido = ? where id = ?",
                batch);
        return updateCounts;
	}
	
	public int[] batchMay1(final List<Usuario> usuarios) {
		int[] updateCounts = jdbcTemplate.batchUpdate(
				"update usuario set nombre = ?, "
						+ "apellido = ? where id = ?",
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setString(1, usuarios.get(i).getNombre());
						ps.setString(2, usuarios.get(i).getApellido());
						ps.setLong(3, usuarios.get(i).getId());
					}

					public int getBatchSize() {
						return usuarios.size();
					}
				});
		return updateCounts;
	}
	


    public int[] batchMay2(final List<Usuario> usuarios) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(usuarios.toArray());
        int[] updateCounts = namedParameterJdbcTemplate.batchUpdate(
                "update usuario set nombre = :nombre, apellido = :apellido where id = :id",
                batch);
        return updateCounts;
    }
	
}
