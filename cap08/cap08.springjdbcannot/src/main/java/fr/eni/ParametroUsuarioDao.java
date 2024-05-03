package fr.eni;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
@Repository
public class ParametroUsuarioDao {
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
  public void setDataSource(DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * DOCUMENT ME!
   *
   * @param nombre DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public int contarPorNombre(String nombre) {
    String sql = "select count(*) from usuario where nombre = :nombre";

    Map<String, String> namedParameters = Collections.singletonMap("nombre", nombre);

    return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
      Integer.class);
  }

  /**
   * DOCUMENT ME!
   *
   * @param exUtil DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public int contarPorEjemplo(Usuario exUtil) {
    String sql = "select count(*) from usuario where nombre = :nombre and apellido = :apellido";

    SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(exUtil);

    return namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
      Integer.class);
  }
}
