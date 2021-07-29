package ru.vladigeras.springgrpc.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class Logger {

    private static ObjectMapper objectMapper;

    public Logger(ObjectMapper objectMapper) {
        Logger.objectMapper = objectMapper;
    }

    @SneakyThrows
    public static <T> String toJson(T o) {
        return objectMapper.writeValueAsString(o);
    }
}
