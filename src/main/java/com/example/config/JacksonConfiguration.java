package com.example.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Configuration
public class JacksonConfiguration {

    @Bean
    Jackson2ObjectMapperBuilderCustomizer offsetDateTimeAsTimestampSerializer() {
        return builder -> builder.serializerByType(OffsetDateTime.class, new OffsetDateTimeAsTimestampSerializer());
    }

    @Bean
    Jackson2ObjectMapperBuilderCustomizer offsetDateTimeAsTimestampDeserializer() {
        return builder -> builder.deserializerByType(OffsetDateTime.class, new OffsetDateTimeAsTimestampDeserializer());
    }

    public static class OffsetDateTimeAsTimestampSerializer extends JsonSerializer<OffsetDateTime> {
        @Override
        public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeNumber(value.toInstant().toEpochMilli());
        }
    }

    public static class OffsetDateTimeAsTimestampDeserializer extends JsonDeserializer<OffsetDateTime> {
        @Override
        public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return Instant.ofEpochMilli(p.getNumberValue().longValue()).atOffset(ZoneOffset.UTC);
        }
    }

}
