package lk.elevenzcode.healthcare.appointmentapi.config.security;

import static java.nio.charset.StandardCharsets.UTF_8;

import lk.elevenzcode.healthcare.appointmentapi.config.property.SecurityProperties;
import lk.elevenzcode.healthcare.appointmentapi.web.util.Constant;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;
import javax.annotation.PostConstruct;

/**
 * Created by හShaන් සNදීප on 3/9/2020 2:04 PM
 */
@Configuration
@EnableResourceServer
@EnableConfigurationProperties(SecurityProperties.class)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
  private static final String ROLE_APPT = "APPT";
  private static final String ROLE_GET_APPT = "GET_APPT";
  private static final String ROLE_GET_PT_APPT = "GET_PT_APPT";
  private static final String ROLE_MAKE_APPT = "MAKE_APPT";
  private static final String ROLE_UPDATE_APPT = "UPDATE_APPT";
  private static final String ROLE_CANCEL_APPT = "CANCEL_APPT";
  private final SecurityProperties securityProperties;
  @Value("${spring.jersey.application-path}")
  private String serviceContext;
  private String API_BASE_PATTERN;
  private TokenStore tokenStore;

  public ResourceServerConfig(final SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @PostConstruct
  void init() {
    API_BASE_PATTERN = String.format("%s/%s/%s", serviceContext, Constant.API_VER,
        Constant.API_PATH);
  }

  @Override
  public void configure(final ResourceServerSecurityConfigurer resources) {
    resources.tokenStore(tokenStore());
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.anonymous().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, API_BASE_PATTERN + "/heartbeat").hasRole(ROLE_APPT)
        .antMatchers(HttpMethod.GET, API_BASE_PATTERN + "/sessions/{id}").hasRole(ROLE_GET_APPT)
        .antMatchers(HttpMethod.GET, API_BASE_PATTERN + "/patients/{id}").hasRole(ROLE_GET_PT_APPT)
        .antMatchers(HttpMethod.GET, API_BASE_PATTERN, API_BASE_PATTERN + "/{id}").hasRole(ROLE_GET_APPT)
        .antMatchers(HttpMethod.POST, API_BASE_PATTERN).hasRole(ROLE_MAKE_APPT)
        .antMatchers(HttpMethod.PUT, API_BASE_PATTERN).hasRole(ROLE_UPDATE_APPT)
        .antMatchers(HttpMethod.DELETE, API_BASE_PATTERN).hasRole(ROLE_CANCEL_APPT)
        .anyRequest()
        .authenticated();
  }

  @Bean
  public DefaultTokenServices tokenServices(final TokenStore tokenStore) {
    DefaultTokenServices tokenServices = new DefaultTokenServices();
    tokenServices.setTokenStore(tokenStore);
    return tokenServices;
  }

  @Bean
  public TokenStore tokenStore() {
    if (tokenStore == null) {
      tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
    }
    return tokenStore;
  }

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setVerifierKey(getPublicKeyAsString());
    return converter;
  }

  private String getPublicKeyAsString() {
    try {
      return IOUtils.toString(securityProperties.getJwt().getPublicKey().getInputStream(), UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
