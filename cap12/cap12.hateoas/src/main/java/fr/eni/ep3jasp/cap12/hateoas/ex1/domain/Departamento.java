package fr.eni.ep3jasp.cap12.hateoas.ex1.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

// tag::code[]
@Entity
@Table(name = "departamento")
public class Departamento implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "code")
  private String code;

  @Column(name = "name")
  private String name;

  @ManyToOne
  private Region region;

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public Departamento code(String code) {
    this.code = code;
    return this;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public Departamento nom(String name) {
    this.nom = name;
    return this;
  }

  public void setName(String name) {
    this.nom = name;
  }

  public Region getRegion() {
    return region;
  }

  public Departamento region(Region region) {
    this.region = region;
    return this;
  }

  public void setRegion(Region region) {
    this.region = region;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Departamento departamento = (Departamento) o;
    if (departamento.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), departamento.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "Departamento{" +
          "id=" + getId() +
          ", code='" + getCode() + "'" +
          ", name='" + getName() + "'" +
          "}";
  }
}
// end::code[]