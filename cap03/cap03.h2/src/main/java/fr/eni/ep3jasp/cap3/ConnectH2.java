package fr.eni.ep3jasp.cap3;

import org.h2.tools.DeleteDbFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Conexión H2
 *
 * @author $author$
 * @version $Revision$
  */
public class ConnectH2 {
  /**
   * Punto de entrada
   *
   * @throws Exception Exception producida
   */
  public static void main(String... args) throws Exception {
    //Eliminamos la base de datos
    DeleteDbFiles.execute("~", "test", true);

    //Conexión
    Class.forName("org.h2.Driver");

    Connection connection = DriverManager.getConnection("jdbc:h2:~/test");
    Statement statement = connection.createStatement();

    //Creación de la tabla
    statement.execute(
      "create table test(id int primary key, name varchar(255))");
    //Inserción de un registro
    statement.execute("insert into test values(1, 'Ediciones ENI')");

    //Lectura del registro
    ResultSet rs;
    //Visualización
    rs = statement.executeQuery("select * from test");

    while (rs.next()) {
      System.out.println(rs.getString("name"));
    }

    //Cierre Statement 
    statement.close();
    //Cierre base
    connection.close();
  }
}
