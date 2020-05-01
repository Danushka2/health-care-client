package lk.elevenzcode.healthcare.clientapp.config.security;

import static java.nio.charset.StandardCharsets.UTF_8;

import lk.elevenzcode.healthcare.clientapp.config.property.SecurityProperties;
import org.apache.commons.io.IOUtils;
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

/**
 * Created by හShaන් සNදීප on 3/21/2020 1:05 PM
 */
@Configuration
@EnableResourceServer
@EnableConfigurationProperties(SecurityProperties.class)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
  private final SecurityProperties securityProperties;
  private TokenStore tokenStore;

  public ResourceServerConfig(final SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Override
  public void configure(final ResourceServerSecurityConfigurer resources) {
    resources.tokenStore(tokenStore());
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/app/**", "/webjars/**", "/js/**", "/css/**", "/font/**",
            "/favicon.ico", "/img/**", "/oauth/**", "/payments/js/**", "/payments/css/**",
            "/payments/html/**")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/patients")
        .permitAll()
        .and()
        .authorizeRequests()
        .antMatchers("/**")
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
