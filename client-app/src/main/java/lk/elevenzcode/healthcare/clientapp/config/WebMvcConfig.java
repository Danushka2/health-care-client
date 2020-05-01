package lk.elevenzcode.healthcare.clientapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by හShaන් සNදීප on 5/1/2020 11:09 AM
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/app/login").setViewName("login");
    registry.addViewController("/").setViewName("home");
    registry.addViewController("/app/home").setViewName("home");
    registry.addViewController("/app/hospitals").setViewName("hospital");
    registry.addViewController("/app/doctors").setViewName("doctor");
    registry.addViewController("/app/patients").setViewName("patient");
    registry.addViewController("/app/appointments").setViewName("appointment");
    registry.addViewController("/app/payments").setViewName("payment");
  }
}
