package fr.eni.ep3jasp.cap05.kotlin.spring5.mvc.app.config

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import fr.eni.ep3jasp.cap05.kotlin.spring5.mvc.app.util.JSR310DateTimeSerializer
import fr.eni.ep3jasp.cap05.kotlin.spring5.mvc.app.util.JSR310LocalDateDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.time.*

// tag::sbmvckt08[]
@Configuration
open class JacksonConfiguration {
    @Bean
    open fun jackson2ObjectMapperBuilder(): Jackson2ObjectMapperBuilder {
        val module = JavaTimeModule()
        module.addSerializer(OffsetDateTime::class.java, JSR310DateTimeSerializer.INSTANCE)
        module.addSerializer(ZonedDateTime::class.java, JSR310DateTimeSerializer.INSTANCE)
        module.addSerializer(LocalDateTime::class.java, JSR310DateTimeSerializer.INSTANCE)
        module.addSerializer(Instant::class.java, JSR310DateTimeSerializer.INSTANCE)
        module.addDeserializer(LocalDate::class.java, JSR310LocalDateDeserializer.INSTANCE)
        return Jackson2ObjectMapperBuilder()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .findModulesViaServiceLoader(true)
                .modulesToInstall(module, KotlinModule())
    }
}
// end::sbmvckt08[]
