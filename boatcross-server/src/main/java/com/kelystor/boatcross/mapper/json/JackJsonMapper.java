package com.kelystor.boatcross.mapper.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JackJsonMapper implements JsonMapper {
    private static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public <T> T toObject(String content, Class<T> valueType) {
        try {
            return MAPPER.readValue(content, valueType);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}
