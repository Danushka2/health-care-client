package lk.elevenzcode.healthcare.commons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:15 PM
 */
public class JsonUtil {
  private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
  private static final ObjectMapper MAPPER = new ObjectMapper();

  public static String toString(Object obj) {
    String jsonStr = null;
    try {
      jsonStr = MAPPER.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
    }
    return jsonStr;
  }

  public static <T> T convertValue(Object ob, Class<T> cls) {
    return MAPPER.convertValue(ob, cls);
  }
}
