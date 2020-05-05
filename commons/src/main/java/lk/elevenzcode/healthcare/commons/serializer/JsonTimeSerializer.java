package lk.elevenzcode.healthcare.commons.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lk.elevenzcode.healthcare.commons.util.DateUtil;

import java.io.IOException;
import java.time.LocalTime;

/**
 * Created by හShaන් සNදීප on 5/5/2020 9:41 PM
 */
public class JsonTimeSerializer extends StdSerializer<LocalTime> {
  public JsonTimeSerializer() {
    this(null);
  }

  public JsonTimeSerializer(Class<LocalTime> d) {
    super(d);
  }

  @Override
  public void serialize(LocalTime time, JsonGenerator generator, SerializerProvider provider) throws IOException {
    if (time != null) {
      generator.writeString(DateUtil.formatTime(time));
    }
  }
}
