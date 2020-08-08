package ru.achernyavskiy0n.springboot.TestUtil.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static ru.achernyavskiy0n.springboot.TestUtil.json.JacksonObjectMapper.getMapper;

/**
 * 05.08.2020
 *
 * @author a.chernyavskiy0n
 */
public class JsonUtils {
    public static String convertObjectToJson(Object object) {
        ObjectMapper mapper = new JacksonObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = new JacksonObjectMapper().writer().withDefaultPrettyPrinter();
        String json;
        try {
            json = ow.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return convertObjectToJson(e);
        }
        return json;
    }

    public static <T> T convertJsonToObject(Class<T> clazz, ResultActions action) throws UnsupportedEncodingException {
        String jsonString = action.andReturn().getResponse().getContentAsString();
        try {
            ObjectMapper mapper = new JacksonObjectMapper();
            return (T) mapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return getMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> T readFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return readValue(getContent(result), clazz);
    }

    public static String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    public static <T> List<T> readListFromJsonMvcResult(MvcResult result, Class<T> clazz) throws UnsupportedEncodingException {
        return readValues(getContent(result), clazz);
    }

    public static <T> List<T> readValues(String json, Class<T> clazz) {
        ObjectReader reader = getMapper().readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }
}
