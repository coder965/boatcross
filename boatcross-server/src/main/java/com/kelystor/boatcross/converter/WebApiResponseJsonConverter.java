package com.kelystor.boatcross.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kelystor.boatcross.dto.WebApiResponse;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;

public class WebApiResponseJsonConverter extends MappingJackson2HttpMessageConverter {
    public WebApiResponseJsonConverter() {
    }

    public WebApiResponseJsonConverter(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (type.getTypeName().equals(WebApiResponse.class.getCanonicalName())) {
            super.writeInternal(object, type, outputMessage);
            return;
        }

        super.writeInternal(WebApiResponse.success(object), WebApiResponse.class, outputMessage);
    }
}
