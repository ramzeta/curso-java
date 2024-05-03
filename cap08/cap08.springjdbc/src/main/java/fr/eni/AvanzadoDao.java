package fr.eni;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
@Repository
public class AvanzadoDao {
  //private BasicDataSource dataSource;
  /**
   * DOCUMENT ME!
   */
  private JdbcTemplate jdbcTemplate;

  /**
   * DOCUMENT ME!
   */
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  /**
   * DOCUMENT ME!
   *
   * @param dataSource DOCUMENT ME!
   */
  @Autowired
  public void setDataSource(BasicDataSource dataSource) {
    log.info("por setter setDataSource");
    //this.dataSource = dataSource;
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * DOCUMENT ME!
   *
   * @param actors DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public int[][] batchMayPor100(final Collection<Usuario> actors) {
    int[][] updateCounts = jdbcTemplate.batchUpdate("update usuario set nombre = ?, apellido = ? where id = ?",
        actors, 100,
        new ParameterizedPreparedStatementSetter<Usuario>() {
          public void setValues(PreparedStatement ps, Usuario argument)
            throws SQLException {
            ps.setString(1, argument.getNombre());
            ps.setString(2, argument.getApellido());
            ps.setLong(3, argument.getId());
          }
        });

    return updateCounts;
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public List<Usuario> get5Apellido() {
    List<Usuario> usuarios = jdbcTemplate.query("select nombre, apellido, id from usuario limit 5",
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
}
