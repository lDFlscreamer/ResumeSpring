package com.screamer.resume.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@TestComponent
public class JsonConverter {

    public MediaType getContentType() {
        return APPLICATION_JSON_UTF8;
    }

    private final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    public <T> String convertObjectToJSON(T object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

    public  <T> T convertJsonToMap(String json, Class<T> objectClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.reader().readValue(json, objectClass);
    }

}
