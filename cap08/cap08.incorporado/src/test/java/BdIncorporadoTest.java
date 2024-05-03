import fr.eni.ep3jasp.ComplejoDao;
import fr.eni.ep3jasp.Usuario;
import lombok.extern.slf4j.Slf4j;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;


//@RunWith(SpringJUnit4ClassRunner.class)
/**
 * Pruebas base de datos incorporada
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class BdIncorporadaTest {
  /**
   * La base de datos incorporada
   */
  private EmbeddedDatabase db;

  /**
   * Preparación del test
   */
  @Before
  public void setUp() {
    // creates an HSQL in-memory database populated from default scripts
    // classpath:schema.sql and classpath:data.sql
    db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                                      .addScript("mi-esquema.sql")
                                      .addScript("mis-datos.sql").build();
  }

  /**
   * DOCUMENT ME!
   */
  @Test
  public void createConnection_should_haveDataTypeFactory() {
    ComplejoDao dao = new ComplejoDao();
    dao.setDataSource(db);

    JdbcTemplate jdbcTemplate = new JdbcTemplate(db);

    List<Usuario> usuarios = dao.getAll();
    log.debug(usuarios.toString());
  }

  /**
   * DOCUMENT ME!
   */
  @After
  public void tearDown() {
    db.shutdown();
  }
}
