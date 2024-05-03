package fr.eni.ep3jasp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * DOCUMENT ME!
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
}
