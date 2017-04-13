package sendotp.util;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * @author gauravmahawar
 */
public class JsonUtil {

  public static <T> T parseJson(String json, Class<T> clazz) throws IOException {
    if (json == null) {
      return null;
    }
    return new ObjectMapper().readValue(json, clazz);

  }

  public static String toJsonAsString(Object arg) throws JsonProcessingException {
    if (arg == null) {
      return null;
    }
    return new ObjectMapper()
      .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
      .writeValueAsString(arg).replaceAll("\\\\", "");
  }
}
