package fr.eni.ep3jasp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;


/**
 * Tarjeta de crédito
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaDeCredito {
  /**
   * Primera tupla
   */
  private int primeraTupla;

  /**
   * Segunda tupla
   */
  private int segundaTupla;

  /**
   * Tercera tupla
   */
  private int terceraTupla;

  /**
   * Cuarta tupla
   */
  private int CuartaTupla;
}
