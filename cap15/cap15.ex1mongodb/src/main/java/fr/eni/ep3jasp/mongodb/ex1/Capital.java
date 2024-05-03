package fr.eni.ep3jasp.mongodb.ex1;

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

//    public String getId() {
//        return id;
//    }

//    @Override
//    public String toString() {
//        return String.format(
//                "Capital[id=%s, pais='%s', capital='%s', continente='%s'",
//                id, pais, capital, continente);
//    }
}
// end::capital[]

