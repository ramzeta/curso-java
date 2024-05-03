package fr.eni.ep3jasp.mongodb.ex2;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

// tag::capital[]
@Getter
@ToString
public class Capital {
    @Id
    private String id;

    private String pais;
    private String capital;
    private String continente;

    public Capital() {}

    public Capital(String pais, String capital, String continente) {
        this.pais = pais;
        this.capital = capital;
        this.continente = continente;
    }
}
// end::capital[]

