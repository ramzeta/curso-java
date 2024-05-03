package fr.eni;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
@Repository
public class JdbcUsuarioDao {
  /**
   * DOCUMENT ME!
   */
  private BasicDataSource dataSource;

  /**
   * DOCUMENT ME!
   */
  private JdbcTemplate jdbcTemplate;

  /**
   * Creates a new JdbcUsuarioDao object.
   */
  public JdbcUsuarioDao() {
    super();
  }

  /**
   * Creates a new JdbcUsuarioDao object.
   *
   * @param dataSource DOCUMENT ME!
   */
  public JdbcUsuarioDao(BasicDataSource dataSource) {
    super();
    log.info("por constructor JdbcUsuarioDao");
    this.dataSource = dataSource;
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  /**
   * DOCUMENT ME!
   *
   * @param dataSource DOCUMENT ME!
   */
  @Autowired
  public void setDataSource(BasicDataSource dataSource) {
    log.info("por setter setDataSource");
    this.dataSource = dataSource;
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public List<Usuario> getAll() {
    List<Usuario> usuarios = jdbcTemplate.query("select nombre, apellido from usuario",
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
