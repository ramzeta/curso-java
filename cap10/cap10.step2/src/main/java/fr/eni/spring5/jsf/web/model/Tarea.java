package fr.eni.spring5.jsf.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
@Entity
public class Tarea {
  /**
   * DOCUMENT ME!
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /**
   * DOCUMENT ME!
   */
  @Column(length = 48)
  private String description;

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public Long getId() {
    return id;
  }

  /**
   * DOCUMENT ME!
   *
   * @param id DOCUMENT ME!
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public String getDescription() {
    return description;
  }

  /**
   * DOCUMENT ME!
   *
   * @param description DOCUMENT ME!
   */
  public void setDescription(String description) {
    this.description = description;
  }
}
