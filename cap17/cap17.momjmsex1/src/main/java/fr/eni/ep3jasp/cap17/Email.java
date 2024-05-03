package fr.eni.ep3jasp.cap17;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// tag::code[]
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    private String to;
    private String body;
}
// end::code[]
