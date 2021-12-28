package dev.jsonone;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

class DeserializerTest {

    @Test
    void testDeserializing() throws JsonMappingException, JsonProcessingException {
        var json = new App().readFile("data.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Item item = mapper.readValue(json, Item.class);
        assertEquals(ZonedDateTime.class, item.someDate.getClass());
        assertEquals(Instant.class, item.someOtherDate.getClass());
        assertEquals(2021, item.someDate.getYear());
        assertEquals(12, item.someOtherDate.atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue());
        assertEquals(1, item.someOtherDate.atZone(ZoneId.systemDefault()).toLocalDateTime().getSecond());
        assertEquals(13, item.someDate.getHour());
        assertEquals("test one", item.title);
    }
}