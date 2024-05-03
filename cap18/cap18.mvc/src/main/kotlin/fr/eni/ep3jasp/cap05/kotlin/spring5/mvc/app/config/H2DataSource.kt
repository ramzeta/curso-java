package fr.eni.ep3jasp.cap05.kotlin.spring5.mvc.app.config

import org.h2.server.web.WebServlet
import org.h2.tools.Server
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

// tag::sbmvckt05[]
@Configuration
@Profile(ProfileExecution.TEST)
@EnableJpaRepositories("fr.eni.kotlin.spring5.mvc.app.repositories")
open class InMemoryDataSource {

    /**
     * Registro del servlet de la consola H2
     * localhost:8080/console
     */
    @Bean
    fun h2servletRegistration(): ServletRegistrationBean<*> {
        val registration = ServletRegistrationBean(WebServlet())
        registration.addUrlMappings("/console/*")
        return registration
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    open fun h2TCPServer(): Server? {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers")
    }
}
// end::sbmvckt05[]


/*
@Bean
open fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
    var entityManagerFactory = LocalContainerEntityManagerFactoryBean()
    var p = Properties()
    p.put("spring.jpa.hibernate.ddl-auto", "create-drop")
    entityManagerFactory.setJpaProperties(p)
    return entityManagerFactory
}
*/