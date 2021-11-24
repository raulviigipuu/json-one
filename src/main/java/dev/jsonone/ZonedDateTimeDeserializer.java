package dev.jsonone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime> {

    public static String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    public ZonedDateTimeDeserializer() {
        this(null);
    }

    protected ZonedDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ZonedDateTime result = null;
        try {
            result = ZonedDateTime.parse(p.getText());
        } catch (DateTimeParseException e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDateTime localDateTime = LocalDateTime.parse(p.getText(), formatter);
            result = localDateTime.atZone(ZoneId.of("UTC"));
        }
        return result;
    }
}
