package com.example.web.jdbc.web.jdbc

import com.fasterxml.jackson.databind.ObjectMapper
import org.postgresql.util.PGobject
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration

abstract class AbstractJsonWritingConverter<T> (
        private val objectMapper: ObjectMapper
) : Converter<T, PGobject> {
    override fun convert(source: T): PGobject {
        val jsonObject = PGobject()
        jsonObject.type = "json"
        jsonObject.value = objectMapper.writeValueAsString(source)
        return jsonObject
    }
}

abstract class AbstractJsonReadingConverter<T>(
        private val objectMapper: ObjectMapper,
        private val valueType: Class<T>
) : Converter<PGobject, T> {
    override fun convert(pgObject: PGobject): T? {
        val source = pgObject.value
        return objectMapper.readValue(source, valueType)
    }
}

@Configuration
class JdbcConfig(private val objectMapper: ObjectMapper) : AbstractJdbcConfiguration() {

    override fun userConverters() = listOf(
        PersonDataWritingConverter(objectMapper), PersonDataReadingConverter(objectMapper),
    )
}

@WritingConverter
class PersonDataWritingConverter(objectMapper: ObjectMapper) : AbstractJsonWritingConverter<BranchData>(objectMapper)

@ReadingConverter
class PersonDataReadingConverter(objectMapper: ObjectMapper) : AbstractJsonReadingConverter<BranchData>(objectMapper, BranchData::class.java)
