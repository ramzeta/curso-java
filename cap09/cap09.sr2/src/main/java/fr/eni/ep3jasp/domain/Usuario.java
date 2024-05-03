package fr.eni.ep3jasp.domain;

import java.util.Objects;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
public class Usuario {
  /**
   * DOCUMENT ME!
   */
  private long id;

  /**
   * DOCUMENT ME!
   */
  private String apellido;

  /**
   * DOCUMENT ME!
   */
  private String nombre;

  @Override
  public String toString() {
    return "Usuario{" +
        "id=" + id +
        ", apellido='" + apellido + '\'' +
        ", nombre='" + nombre + '\'' +
        '}';
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setNombre(String nombre) {
    this.nom = nombre;
  }

  public long getId() {

    return id;
  }

  public String getApellido() {
    return apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public Usuario(long id, String apellido, String nombre) {

    this.id = id;
    this.apellido = apellido;
    this.nom = nombre;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Usuario that = (Usuario) o;
    return id == that.id &&
        Objects.equals(apellido, that.apellido) &&
        Objects.equals(nombre, that.nombre);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, apellido, nombre);
  }
}
