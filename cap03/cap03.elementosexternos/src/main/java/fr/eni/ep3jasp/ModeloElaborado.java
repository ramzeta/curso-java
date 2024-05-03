package fr.eni.ep3jasp;

import java.io.Serializable;

import java.rmi.dgc.VMID;


//https://forum.hibernate.org/viewtopic.php?f=1&t=928172&start=105
/**
 * Modelo mejorado de equals y hascode utilizable con JPA
 *
 * @author $author$
 * @version $Revision$
  */
public class ModeloElaborado implements Serializable {
  /**
   * El vmid
   */
  private volatile VMID vmid;

  /**
   * El id
   */
  private Long id;

  /**
   * getter del id
   *
   * @return id
   */
  public Long getId() {
    return id;
  }

  /**
   * setter del id
   *
   * @param id id
   */
  private void setId(Long id) {
    this.id = id;
  }

  /**
   * el test de igualdad
   *
   * @param obj el objeto a comparar
   *
   * @return true si objetos iguales, false en caso contrario
   */
  public boolean equals(Object obj) {
    final boolean valorDevuelto;

    if (obj instanceof ModeloElaborado) {
      return getVmid().equals(((ModeloElaborado) obj).getVmid());
    } else {
    	valorDevuelto = false;
    }

    return valorDevuelto;
  }

  /**
   * Cálculo del hascode
   *
   * @return El hascode
   */
  public int hashCode() {
    return getVmid().hashCode();
  }

  /**
   * getter del vmid
   *
   * @return del vmid
   */
  private Object getVmid() {
    if ((vmid != null) || ((vmid == null) && (id == null))) {
      if (vmid == null) { //Avoid the performance impact of synchronized if we can

        synchronized (this) {
          if (vmid == null) {
            vmid = new VMID();
          }
        }
      }

      return vmid;
    }

    return id;
  }
}
