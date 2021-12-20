package dev.jsonone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InstantDeserializer extends StdDeserializer<Instant> {

    private static final Logger LOG = LoggerFactory.getLogger(InstantDeserializer.class);

    public static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public InstantDeserializer() {
        this(null);
    }

    protected InstantDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        LOG.debug("TEXT: {}", p.getText());
        Instant result = null;
        try {
            result = Instant.parse(p.getText());
        } catch (DateTimeParseException e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDateTime localDateTime = LocalDateTime.parse(p.getText(), formatter);
            result = localDateTime.toInstant(ZoneOffset.UTC);
        }
        return result;
    }
}
