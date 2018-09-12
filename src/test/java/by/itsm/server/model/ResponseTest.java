package by.itsm.server.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ResponseTest {

    private static ObjectMapper objectMapper;
    private static Response response;
    private static String json;

    @BeforeClass
    public static void setUp() throws Exception {
        objectMapper = new ObjectMapper();
        response = new Response("test message");
        json = "{\"message\":\"test message\"}";
    }

    @Test
    public void toJson() {
        Response source = response;
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
            Response resultResponse = objectMapper.readValue(json, Response.class);
            assertEquals(resultResponse, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}