package fr.eni.ep3jasp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//To ignore any unknown properties in JSON input without exception:
/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
@JsonIgnoreProperties(ignoreUnknown = true)
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
