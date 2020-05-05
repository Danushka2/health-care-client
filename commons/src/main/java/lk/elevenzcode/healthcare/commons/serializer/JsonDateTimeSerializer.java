package lk.elevenzcode.healthcare.commons.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lk.elevenzcode.healthcare.commons.util.DateUtil;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by හShaන් සNදීප on 5/5/2020 9:25 PM
 */
public class JsonDateTimeSerializer extends StdSerializer<LocalDateTime> {
  public JsonDateTimeSerializer() {
    this(null);
  }

  public JsonDateTimeSerializer(Class<LocalDateTime> d) {
    super(d);
  }

  @Override
  public void serialize(LocalDateTime date, JsonGenerator generator, SerializerProvider provider) throws IOException {
    if (date != null) {
      generator.writeString(DateUtil.formatDateTime(date));
    }
  }
}
