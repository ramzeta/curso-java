package fr.eni;

import fr.eni.dao.UsuarioDao;
import fr.eni.model.Usuario;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Ejemplo simple Spring Data
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class SpringDataEx1 {
  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   *
   * @throws SQLException La excepción sql enviada
   */
  public static void main(String[] args) throws SQLException {
    log.debug("SpringDataEx1:");
    ClassPathXmlApplicationContext appContext;
    appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    BeanFactory factory = (BeanFactory) appContext;

    UsuarioDao dao = (UsuarioDao) factory.getBean("usuarioDao");

    Usuario u1 = new Usuario(1, "apellido_a", "nombre_a");
    dao.save(u1);
    Usuario u2 = new Usuario(2, "apellido_b", "nombre_a");
    dao.save(u2);

    List<Usuario> usuarios = dao.findByNombreLike("nombre_a");

    log.debug(usuarios.toString());
  }
}
