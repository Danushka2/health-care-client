package lk.elevenzcode.healthcare.commons.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author HaShaN on 8/29/2019 12:45 AM.
 */
public class MoneySerializer extends JsonSerializer<BigDecimal> {
  public static final String PATTERN = ".##";

  @Override
  public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    jgen.writeNumber(new DecimalFormat(PATTERN).format(ConversionUtil.getMoney(value)));
  }
}
