package fr.eni;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString 
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private long id;
    private String apellido;
    private String nombre;
}
