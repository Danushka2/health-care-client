package lk.elevenzcode.healthcare.appointmentapi.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * Created by හShaන් සNදීප on 3/21/2020 3:54 PM
 */
@Configuration
public class OAuth2RestTemplateConfig {
  @Bean
  public OAuth2RestTemplate oauth2RestTemplate(ClientCredentialsResourceDetails details) {
    return new OAuth2RestTemplate(details);
  }
}
