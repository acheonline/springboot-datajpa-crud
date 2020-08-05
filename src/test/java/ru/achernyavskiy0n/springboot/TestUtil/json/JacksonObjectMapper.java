package ru.achernyavskiy0n.springboot.TestUtil.json;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 02.08.2020
 *
 * @author a.chernyavskiy0n
 */
public class JacksonObjectMapper extends ObjectMapper {

    private static final ObjectMapper MAPPER = new JacksonObjectMapper();

    public JacksonObjectMapper() {
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }
}
