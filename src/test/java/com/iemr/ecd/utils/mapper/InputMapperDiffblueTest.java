package com.iemr.ecd.utils.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import org.apache.logging.slf4j.Log4jLogger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InputMapper.class})
@ExtendWith(SpringExtension.class)
class InputMapperDiffblueTest {
    @Autowired
    private InputMapper inputMapper;

    /**
     * Method under test: {@link InputMapper#gson()}
     */
    @Test
    void testGson() {
        // Arrange, Act and Assert
        assertTrue(InputMapper.gson().logger instanceof Log4jLogger);
    }

    /**
     * Method under test: {@link InputMapper#fromJson(JsonElement, Class)}
     */
    @Test
    void testFromJson() throws Exception {
        // Arrange
        JsonArray json = new JsonArray(3);
        Class<Object> classOfT = Object.class;

        // Act and Assert
        assertTrue(((List<Object>) inputMapper.fromJson(json, classOfT)).isEmpty());
    }

    /**
     * Method under test: {@link InputMapper#fromJson(JsonElement, Class)}
     */
    @Test
    void testFromJson2() throws Exception {
        // Arrange
        JsonArray json = new JsonArray(1);
        Class<Object> classOfT = Object.class;

        // Act and Assert
        assertTrue(((List<Object>) inputMapper.fromJson(json, classOfT)).isEmpty());
    }

    /**
     * Method under test: {@link InputMapper#fromJson(JsonElement, Class)}
     */
    @Test
    void testFromJson3() throws Exception {
        // Arrange
        Class<Object> classOfT = Object.class;

        // Act and Assert
        assertNull(inputMapper.fromJson((JsonElement) null, classOfT));
    }

    /**
     * Method under test: {@link InputMapper#fromJson(JsonElement, Class)}
     */
    @Test
    void testFromJson4() throws Exception {
        // Arrange
        JsonNull json = new JsonNull();
        Class<Object> classOfT = Object.class;

        // Act and Assert
        assertNull(inputMapper.fromJson(json, classOfT));
    }

    /**
     * Method under test: {@link InputMapper#fromJson(JsonElement, Class)}
     */
    @Test
    void testFromJson5() throws Exception {
        // Arrange
        JsonObject json = new JsonObject();
        Class<Object> classOfT = Object.class;

        // Act and Assert
        assertTrue(((LinkedTreeMap<Object, Object>) inputMapper.fromJson(json, classOfT)).isEmpty());
    }

    /**
     * Method under test: {@link InputMapper#fromJson(JsonElement, Class)}
     */
    @Test
    void testFromJson6() throws Exception {
        // Arrange
        JsonPrimitive json = new JsonPrimitive("String");
        Class<Object> classOfT = Object.class;

        // Act and Assert
        assertEquals("String", inputMapper.fromJson(json, classOfT));
    }

    /**
     * Method under test: {@link InputMapper#fromJson(JsonElement, Class)}
     */
    @Test
    void testFromJson7() throws Exception {
        // Arrange
        JsonArray json = new JsonArray(3);
        json.add(true);
        Class<Object> classOfT = Object.class;

        // Act and Assert
        assertEquals(1, ((List<Boolean>) inputMapper.fromJson(json, classOfT)).size());
        assertTrue(((List<Boolean>) inputMapper.fromJson(json, classOfT)).get(0));
    }

    /**
     * Method under test: {@link InputMapper#fromJson(String, Class)}
     */
    @Test
    void testFromJson8() throws Exception {
        // Arrange
        Class<Object> classOfT = Object.class;

        // Act and Assert
        assertEquals("Json", inputMapper.fromJson("Json", classOfT));
    }

    /**
     * Method under test: {@link InputMapper#fromJson(String, Class)}
     */
    @Test
    void testFromJson9() throws Exception {
        // Arrange
        Class<Object> classOfT = Object.class;

        // Act and Assert
        assertNull(inputMapper.fromJson("", classOfT));
    }
}
