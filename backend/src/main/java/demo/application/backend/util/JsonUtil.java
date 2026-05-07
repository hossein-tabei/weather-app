package demo.application.backend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * این متد برای زمانی باید استفاده شود که کلاس مدلی (targetType) که قرار است رشته جیسان (jsonString)
     * به آن تبدیل شود حاوی انوتیشن های کتابخانه jackson-annotations مانند @JsonProperty باشد.
     * در این شرایط حتما باید این متد به کار برده شود که از کلاس ObjectMapper برای تبدیل داده استفاده می کند.
     * در صورت استفاده از مبدل های دیگر مثل Gson، ممکن است داده های غیرمعتبر در خروجی مشاهده شود.
     */
    public static <T> T convertToPOJO(String jsonString, Class<T> targetType) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(jsonString, targetType);
    }

    public static <T> List<T> convertToPOJOList(String jsonString, Class<T> targetType) throws JsonProcessingException {
        return OBJECT_MAPPER.readerForArrayOf(targetType).readValue(jsonString);
    }

}
