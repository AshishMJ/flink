package com.ashish.flink.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

public class CustomDeserializer implements DeserializationSchema<MessageLog<String>> {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public MessageLog<String> deserialize(byte[] message) throws IOException {
        return objectMapper.readValue(message, new TypeReference<MessageLog<String>>() {});
    }

    @Override
    public boolean isEndOfStream(MessageLog<String> nextElement) {
        return false;
    }

    @Override
    public TypeInformation<MessageLog<String>> getProducedType() {
        return TypeInformation.of(new TypeHint<MessageLog<String>>() {
        });
    }
}
