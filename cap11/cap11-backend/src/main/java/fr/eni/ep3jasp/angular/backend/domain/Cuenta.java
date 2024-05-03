package fr.eni.ep3jasp.angular.backend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude={"id"})
@Entity
public class Cuenta {
  @Id
  @GeneratedValue
  private Long id;
  private Integer numero;
  private String nombre;

  public Cuenta(Integer numero, String nombre) {
    this.id=null;
    this.numero=numero;
    this.nombre=nombre;
  }
}
