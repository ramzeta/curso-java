package fr.eni.ep3jasp.singleton;

import org.springframework.stereotype.Service;

/**
 * El singleton
 *
 * @author $author$
 * @version $Revision$
  */
@Service("servicioUnico")
public class servicioUnico {
  /**
   * Estado del singleton
   */
  public int estado = 0;

  /**
   * Incremento sincronizado
   *
   * @return estado incrementado
   */
  public synchronized int incEstado() {
    //Operación larga
    return ++estado;
  }

  /**
   * getter del estado
   *
   * @return estado
   */
  public int getEstado() {
    return estado;
  }

  /**
   * setter del estado (sincronizado)
   *
   * @param el estado
   */
  public synchronized void setEstado(int estado) {
    this.estado = estado;
  }

  /**
   * Visualización del estado
   */
  void VerEstado() {
    System.out.println(estado);
  }

  /**
   * Cálculo del hascode
   *
   * @return el hashcode
   */
  @Override
  public int hashCode() {
    final int primo = 31;
    int resultado = 1;
    resultado = (primo * resultado) + estado;

    return resultado;
  }

  /**
   * Formateo para visualziación
   *
   * @return la cadena formateada.
   */
  @Override
  public String toString() {
    return "ServicioUnico [estado=" + estado + "]";
  }

  /**
   * igualdad
   *
   * @param obj Objeto a comparar
   *
   * @return el resultado de la comparación
   */
  @Override
  public boolean equals(Object obj) {
    ServicioUnico other = (ServicioUnico) obj;

    if (estado != other.estado) {
      return false;
    }

    return true;
  }
}

