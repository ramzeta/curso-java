package fr.eni;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
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
   * DOCUMENT ME!
   */
  private SimpleJdbcInsert insertUsuario;

  /**
   * Creates a new JdbcUsuarioDao object.
   */
  public JdbcUsuarioDao() {
    super();
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
    this.insertUsuario = new SimpleJdbcInsert(dataSource).withTableName(
        "Usuario").usingColumns("nombre", "apellido")
                                                             .usingGeneratedKeyColumns("id");
  }

  /**
   * DOCUMENT ME!
   *
   * @param usuario DOCUMENT ME!
   */
  public void agregar(Usuario usuario) {
    Map<String, Object> parameters = new HashMap<String, Object>(3);
    parameters.put("nombre", usuario.getNombre());
    parameters.put("apellido", usuario.getApellido());
    insertUsuario.execute(parameters);
  }

  /**
   * DOCUMENT ME!
   *
   * @param usuario DOCUMENT ME!
   */
  public void agregar2(Usuario usuario) {
    Map<String, Object> parameters = new HashMap<String, Object>(2);
    parameters.put("nombre", usuario.getNombre());
    parameters.put("apellido", usuario.getApellido());
    parameters.put("id", usuario.getId());

    Number newId = insertUsuario.executeAndReturnKey(parameters);
    usuario.setId(newId.longValue());
  }

  /**
   * DOCUMENT ME!
   *
   * @param usuario DOCUMENT ME!
   */
  public void agregar3(Usuario usuario) {
    SqlParameterSource parameters = new BeanPropertySqlParameterSource(usuario);
    Number newId = insertUsuario.executeAndReturnKey(parameters);
    usuario.setId(newId.longValue());
  }

  /**
   * DOCUMENT ME!
   *
   * @param usuario DOCUMENT ME!
   */
  public void agregar4(Usuario usuario) {
    SqlParameterSource parameters = new MapSqlParameterSource().addValue("nombre",
        usuario.getNombre()).addValue("apellido", usuario.getApellido());
    Number newId = insertUsuario.executeAndReturnKey(parameters);
    usuario.setId(newId.longValue());
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public List<Usuario> getAll() {
    List<Usuario> usuarios = jdbcTemplate.query("select nombre, apellido, id from usuario",
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
