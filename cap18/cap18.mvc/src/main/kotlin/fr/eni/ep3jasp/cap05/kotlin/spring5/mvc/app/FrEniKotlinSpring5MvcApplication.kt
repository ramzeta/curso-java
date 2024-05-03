package fr.eni.ep3jasp.cap05.kotlin.spring5.mvc.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

// tag::sbmvckt01[]
@SpringBootApplication
class FrEniKotlinSpring5MvcApplication
fun main(args: Array<String>) {
    runApplication<FrEniKotlinSpring5MvcApplication>(*args)
}
// end::sbmvckt01[]

