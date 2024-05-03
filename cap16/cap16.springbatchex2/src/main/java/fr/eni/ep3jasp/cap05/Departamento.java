package fr.eni.ep3jasp.cap05;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// tag::code[]
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {
  private String code;
  private String nombre;
}
// end::code[]