package fr.eni.ep3jasp.cap05.kotlin.spring5.mvc.app.repositories

import fr.eni.ep3jasp.cap05.kotlin.spring5.mvc.app.domain.Cuenta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

// tag::sbmvckt10[]
@Repository
interface CuentaRepository : JpaRepository<Cuenta, UUID>
// end::sbmvckt10[]