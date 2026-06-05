package demo.application.backend.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestUtilHelper {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
            .create();

    public static <T> T deepCopy(T original) {
        Class<T> targetType = (Class<T>)original.getClass();
        return gson.fromJson(gson.toJson(original), targetType);
    }

    public static <T> String toJson(T original) {
        return gson.toJson(original);
    }

}
