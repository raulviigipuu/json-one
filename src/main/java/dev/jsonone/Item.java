package dev.jsonone;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;

public class Item {

    public String title;
    @JsonDeserialize(using = InstantDeserializer.class)
    public Instant someDate;
}
