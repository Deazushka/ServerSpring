package by.itsm.server.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class RequestTest {
    private static ObjectMapper objectMapper;
    private static Request request;
    private static String json;

    @BeforeClass
    public static void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        request = new Request("test name", "test message");
        json = "{\"name\":\"test name\",\"message\":\"test message\"}";
    }

    @Test
    public void toJson() {
        Request source = request;
        try {
            String resultJson = objectMapper.writeValueAsString(source);
            assertEquals(resultJson, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fromJson() {
        try {
            Request resultRequest = objectMapper.readValue(json, Request.class);
            assertEquals(resultRequest, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}