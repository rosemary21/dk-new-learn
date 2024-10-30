package com.example.dklearn.util;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@Converter
public class LocalDateTimeConverters implements AttributeConverter<LocalDateTime, Date> {
    public LocalDateTimeConverters() {
    }

    public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        } else {
            Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
            return Date.from(instant);
        }
    }

    public LocalDateTime convertToEntityAttribute(Date date) {
        if (date == null) {
            return null;
        } else {
            Instant instant = Instant.ofEpochMilli(date.getTime());
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }
    }
}
