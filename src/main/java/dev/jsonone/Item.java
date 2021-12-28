package dev.jsonone;

import java.time.Instant;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Item {

    public String title;
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    public ZonedDateTime someDate;
    @JsonDeserialize(using = InstantDeserializer.class)
    public Instant someOtherDate;
}
