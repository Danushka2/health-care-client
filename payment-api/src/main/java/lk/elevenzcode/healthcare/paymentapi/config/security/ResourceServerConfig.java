package lk.elevenzcode.healthcare.paymentapi.config.security;

import static java.nio.charset.StandardCharsets.UTF_8;

import lk.elevenzcode.healthcare.paymentapi.config.property.SecurityProperties;
import lk.elevenzcode.healthcare.paymentapi.web.util.Constant;
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
  private static final String ROLE_CLIENT = "CLIENT";
  private static final String ROLE_PAY = "PAY";
  private static final String ROLE_MAKE_PAY = "MAKE_PAY";
  private static final String ROLE_GET_ALL_PAY = "GET_ALL_PAY";
  private static final String ROLE_GET_APPT_PAY = "GET_APPT_PAY";
  private static final String ROLE_GET_APPT = "GET_APPT";
  private static final String ROLE_REFUND_PAY = "REFUND_PAY";
  private static final String ROLE_GET_PAY = "GET_PAY";
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
        .antMatchers("/js/**")
        .permitAll()
        .antMatchers(HttpMethod.GET, API_BASE_PATTERN + "/heartbeat").hasAnyRole(ROLE_PAY)
        .antMatchers(HttpMethod.POST, API_BASE_PATTERN + "/init").hasRole(ROLE_MAKE_PAY)
        .antMatchers(HttpMethod.PUT, API_BASE_PATTERN + "/complete").hasRole(ROLE_MAKE_PAY)
        .antMatchers(HttpMethod.GET, API_BASE_PATTERN).hasRole(ROLE_GET_ALL_PAY)
        .antMatchers(HttpMethod.GET, API_BASE_PATTERN + "/appointments/{apptId}")
        .access("hasRole('" + ROLE_GET_APPT_PAY + "') AND hasRole('" + ROLE_GET_APPT + "')")
        .antMatchers(HttpMethod.DELETE, API_BASE_PATTERN + "/appointments/{apptId}").hasAnyRole(ROLE_REFUND_PAY, ROLE_CLIENT)
        .antMatchers(HttpMethod.GET, API_BASE_PATTERN + "/{id}").hasRole(ROLE_GET_PAY)
        .antMatchers(HttpMethod.DELETE, API_BASE_PATTERN + "/{id}").hasRole(ROLE_REFUND_PAY)
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
      return IOUtils.toString(securityProperties.getJwt().getPublicKey().getInputStream(),
          UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
