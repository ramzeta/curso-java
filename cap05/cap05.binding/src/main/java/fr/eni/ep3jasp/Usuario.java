package fr.eni.ep3jasp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Clase usuario
 *
 * @author $author$
 * @version $Revision$
  */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
  /**
   * El id
   */
  private long id;

  /**
   * El apellido
   */
  private String apellido;

  /**
   * El nombre
   */
  private String nombre;
}
