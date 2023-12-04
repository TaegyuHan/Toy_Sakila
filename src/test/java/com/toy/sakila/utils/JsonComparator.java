package com.toy.sakila.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonComparator {

    /**
     * 비교할 두 JSON 문자열을 받아서, 동일한지 여부를 반환하는 함수입니다.
     *
     * @param actualJson 실제 응답으로 받은 JSON 문자열
     * @param expectedJsonString 기대하는 JSON 형태의 문자열
     * @return 두 JSON 객체가 동일하면 true, 그렇지 않으면 false를 반환합니다.
     */
    public static boolean compareJsonStrings(String actualJson, String expectedJsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode tree1 = objectMapper.readTree(actualJson);
        JsonNode tree2 = objectMapper.readTree(expectedJsonString);
        return tree1.equals(tree2);
    }

    public static void assertJsonEquals(String actualJson, String expectedJson) throws JsonProcessingException {
        assertThat(compareJsonStrings(actualJson, expectedJson))
                .as("두 JSON 문자열이 동일한지 비교합니다.")
                .isTrue();
    }
}