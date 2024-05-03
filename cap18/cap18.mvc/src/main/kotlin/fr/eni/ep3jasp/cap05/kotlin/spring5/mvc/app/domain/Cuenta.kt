package fr.eni.ep3jasp.cap05.kotlin.spring5.mvc.app.domain

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id



// tag::sbmvckt09[]
@Entity
data class Cuenta(
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        @Type(type = "uuid-char")
        var id: UUID? = null,
        var aperturaDateTime: LocalDateTime = LocalDateTime.now(),
        var nombre: String = ""
)
// end::sbmvckt09[]
