package fr.eni.ep3jasp.cap05.kotlin.spring5.mvc.app.util

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

//FIXME:Retomar implem jHipster
// tag::sbmvckt07[]
class JSR310LocalDateDeserializer private constructor() : JsonDeserializer<LocalDate>() {
    companion object {
        val INSTANCE = JSR310LocalDateDeserializer()
        private val ISO_DATE_OPTIONAL_TIME: DateTimeFormatter
        init {
            ISO_DATE_OPTIONAL_TIME = DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE).optionalStart().appendLiteral('T').append(DateTimeFormatter.ISO_OFFSET_TIME).toFormatter()
        }
    }

    @Throws(IOException::class)
    override fun deserialize(parser: JsonParser, context: DeserializationContext): LocalDate? {
        when (parser.currentToken) {
            JsonToken.START_ARRAY -> {
                if (parser.nextToken() == JsonToken.END_ARRAY) {
                    return null
                }
                val year = parser.intValue
                parser.nextToken()
                val month = parser.intValue
                parser.nextToken()
                val day = parser.intValue
                if (parser.nextToken() != JsonToken.END_ARRAY) {
                    throw context.wrongTokenException(parser, JsonToken.END_ARRAY, "Expected array to end.")
                }
                return LocalDate.of(year, month, day)
            }

            JsonToken.VALUE_STRING -> {
                val string = parser.text.trim { it <= ' ' }
                if (string.length == 0) {
                    return null
                }
                return LocalDate.parse(string, ISO_DATE_OPTIONAL_TIME)
            }
        }
        throw context.wrongTokenException(parser, JsonToken.START_ARRAY, "Expected array or string.")
    }
}
// tag::sbmvckt07[]
