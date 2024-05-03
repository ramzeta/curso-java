package fr.eni.ep3jasp.ep3jasp.cap3;

/**
 * Prueba con project
 *
 * @author $author$
 * @version $Revision$
  */
public class Main {
  /**
   * Punto de entrada
   *
   * @param args Los argumentos de la línea de comandos
   */
  public static void main(String[] args) {
    Usuario usuario = new Usuario();
    usuario.setNombre("toto");
    System.out.println(usuario.getNombre());
    usuario = new Usuario(1, "apellido", "nombre");
    System.out.println(usuarior.toString());
  }
}
