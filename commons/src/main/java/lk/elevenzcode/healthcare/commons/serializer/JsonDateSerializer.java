package lk.elevenzcode.healthcare.commons.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lk.elevenzcode.healthcare.commons.util.DateUtil;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by හShaන් සNදීප on 5/5/2020 9:38 PM
 */
public class JsonDateSerializer extends StdSerializer<LocalDate> {

  public JsonDateSerializer() {
    this(null);
  }

  public JsonDateSerializer(Class<LocalDate> d) {
    super(d);
  }

  @Override
  public void serialize(LocalDate date, JsonGenerator generator, SerializerProvider provider) throws IOException {
    if (date != null) {
      generator.writeString(DateUtil.formatDate(date));
    }
  }
}
