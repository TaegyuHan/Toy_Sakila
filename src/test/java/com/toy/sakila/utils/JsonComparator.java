package com.toy.sakila.utils;

import org.json.JSONException;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonComparator {

    /**
     * 비교할 두 JSON 문자열을 받아서, 동일한지 여부를 반환하는 함수입니다.
     *
     * @param actualJson 실제 응답으로 받은 JSON 문자열
     * @param expectedJsonString 기대하는 JSON 형태의 문자열
     * @return 두 JSON 객체가 동일하면 true, 그렇지 않으면 false를 반환합니다.
     */
    public static boolean compareJsonStrings(String actualJson, String expectedJsonString) throws JSONException {
        JSONObject actualJSONObject = new JSONObject(actualJson);
        JSONObject expectedJSONObject = new JSONObject(expectedJsonString);
        return actualJSONObject.toString().equals(expectedJSONObject.toString());
    }

    public static void assertJsonEquals(String actualJson, String expectedJson) throws JSONException {
        JSONObject actualJSONObject = new JSONObject(actualJson);
        JSONObject expectedJSONObject = new JSONObject(expectedJson);
        assertEquals(actualJSONObject.toString(), expectedJSONObject.toString());
    }
}