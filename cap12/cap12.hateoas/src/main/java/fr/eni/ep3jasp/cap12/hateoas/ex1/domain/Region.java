package fr.eni.ep3jasp.cap12.hateoas.ex1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

// tag::code[]
@Entity
@Table(name = "region")
@Builder
@NoArgsConstructor
public class Region implements Serializable {

  private static final long serialVersionUID = 1L;

  //Constructor
  public Region(Long id, String code, String nombre, Set<Departamento> departamentos) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.departamentos = departamentos;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "code")
  private String code;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "region")
  @JsonIgnore
  private Set<Departamento> departamentos = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public Region code(String code) {
    this.code = code;
    return this;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getNombre() {
    return name;
  }

  public Region nombre(String name) {
    this.name = name;
    return this;
  }

  public void setNombre(String name) {
    this.name = name;
  }

  public Set<Departamento> getDepartamentos() {
    return departamentos;
  }

  public Region departamentos(Set<Departamento> departamentos) {
    this.departamentos = departamentos;
    return this;
  }

  public Region addDepartamento(Departamento departamento) {
    this.departamentos.add(departamento);
    departamento.setRegion(this);
    return this;
  }

  public Region removeDepartamento(Departamento departamento) {
    this.departamentos.remove(departamento);
    departamento.setRegion(null);
    return this;
  }

  public void setDepartamentos(Set<Departamento> departamentos) {
    this.departamentos = departamentos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Region region = (Region) o;
    if (region.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), region.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "Region{" +
          "id=" + getId() +
          ", code='" + getCode() + "'" +
          ", name='" + getNombre() + "'" +
          "}";
  }
}
// end::code[]