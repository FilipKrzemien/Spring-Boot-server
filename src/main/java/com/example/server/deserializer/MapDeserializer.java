package com.example.server.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class MapDeserializer extends JsonDeserializer<HashMap<Integer, Float>> {
    @Override
    public HashMap<Integer, Float> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        if (jsonParser.getCurrentToken().equals(JsonToken.START_OBJECT)) {
            return mapper.readValue(jsonParser, new TypeReference<HashMap<Integer, Float>>() {
            });
        } else {
         mapper.readTree(jsonParser);
         return new HashMap<Integer, Float>();
        }
    }
}
