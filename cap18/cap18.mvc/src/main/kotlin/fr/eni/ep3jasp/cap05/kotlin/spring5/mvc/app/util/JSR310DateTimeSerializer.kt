package fr.eni.ep3jasp.cap05.kotlin.spring5.mvc.app.util

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.io.IOException
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

//FIXME:Retomar implem jHipster

// tag::sbmvckt06[]
class JSR310DateTimeSerializer private constructor() : JsonSerializer<TemporalAccessor>() {
    companion object {
        private val ISOFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"))
        val INSTANCE = JSR310DateTimeSerializer()
    }

    @Throws(IOException::class)
    override fun serialize(value: TemporalAccessor, generator: JsonGenerator, serializerProvider: SerializerProvider) {
        generator.writeString(ISOFormatter.format(value))
    }
}
// end::sbmvckt06[]