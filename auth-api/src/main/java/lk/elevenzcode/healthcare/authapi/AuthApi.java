package lk.elevenzcode.healthcare.authapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created by hashan on 2/9/20 11:50 AM
 */
@SpringBootApplication
public class AuthApi extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(AuthApi.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(AuthApi.class);
  }
}
