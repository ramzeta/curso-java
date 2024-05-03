package fr.eni.ep3jasp.cap05.kotlin.spring5.mvc.app.controllers

import fr.eni.ep3jasp.cap05.kotlin.spring5.mvc.app.domain.Cuenta
import fr.eni.ep3jasp.cap05.kotlin.spring5.mvc.app.repositories.CuentaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

// tag::sbmvckt11[]
@RestController
@RequestMapping(value = "\${api.base}", produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
class CuentaController {
    @Autowired
    lateinit private var cuentaRepository: CuentaRepository;
    @RequestMapping("/cuentaes", method = arrayOf(RequestMethod.GET))
    fun getBlogPosts() = cuentaRepository.findAll()

    @RequestMapping("/cuenta/{id}", method = arrayOf(RequestMethod.GET))
    fun getBlogPost(@PathVariable id: UUID) = cuentaRepository.findById(id);

    @RequestMapping("/cuenta/{id}", method = arrayOf(RequestMethod.PATCH))
    fun updateBlogPost(@PathVariable id: UUID, @RequestBody nom: String) {
        var cuenta : Optional<Cuenta> = cuentaRepository.findById(id);
        cuenta.get().nom = nom
        cuentaRepository.save(cuenta.get());
    }

    @RequestMapping("/cuenta/{id}", method = arrayOf(RequestMethod.DELETE))
    fun deleteBlogPost(@PathVariable id: UUID) = cuentaRepository.deleteById(id);

    @RequestMapping(value = "/cuenta", method = arrayOf(RequestMethod.POST))
    fun postBlogPost(@RequestBody nom: String) = cuentaRepository.save(Cuenta(nombre = nombre))
}
// end::sbmvckt11[]