package lk.elevenzcode.healthcare.patientapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created by hashan on 2/28/20 12:23 PM
 */
@SpringBootApplication
public class PatientApi extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(PatientApi.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(PatientApi.class);
  }
}
