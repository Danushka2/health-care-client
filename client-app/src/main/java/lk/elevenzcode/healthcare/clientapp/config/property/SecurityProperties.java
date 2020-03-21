package lk.elevenzcode.healthcare.clientapp.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

/**
 * Created by හShaන් සNදීප on 3/21/2020 1:03 PM
 */
@ConfigurationProperties("security")
public class SecurityProperties {
  private JwtProperties jwt;

  public JwtProperties getJwt() {
    return jwt;
  }

  public void setJwt(JwtProperties jwt) {
    this.jwt = jwt;
  }

  public static class JwtProperties {

    private Resource publicKey;

    public Resource getPublicKey() {
      return publicKey;
    }

    public void setPublicKey(Resource publicKey) {
      this.publicKey = publicKey;
    }
  }
}
