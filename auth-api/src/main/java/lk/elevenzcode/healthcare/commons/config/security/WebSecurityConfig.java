package lk.elevenzcode.healthcare.commons.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by හShaන් සNදීප on 3/9/2020 12:52 PM
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final DataSource dataSource;
  private PasswordEncoder passwordEncoder;
  private UserDetailsService userDetailsService;

  public WebSecurityConfig(final DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService())
        .passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    if (passwordEncoder == null) {
      passwordEncoder = DefaultPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    return passwordEncoder;
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    if (userDetailsService == null) {
      userDetailsService = new JdbcDaoImpl();
      ((JdbcDaoImpl) userDetailsService).setDataSource(dataSource);
    }
    return userDetailsService;
  }

}
