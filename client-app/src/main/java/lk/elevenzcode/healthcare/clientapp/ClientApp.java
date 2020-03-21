package lk.elevenzcode.healthcare.clientapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by හShaන් සNදීප on 3/21/2020 12:29 PM
 */
@EnableZuulProxy
@SpringBootApplication
public class ClientApp {
  public static void main(String[] args) {
    SpringApplication.run(ClientApp.class, args);
  }
}
