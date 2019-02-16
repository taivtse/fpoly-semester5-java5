package vn.edu.fpt.util;

import com.google.gson.Gson;

public class JsonConverterUtil {
    private static Gson gson = new Gson();

    public static <T> String toJson(T obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
