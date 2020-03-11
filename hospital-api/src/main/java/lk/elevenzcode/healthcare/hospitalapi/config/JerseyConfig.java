package lk.elevenzcode.healthcare.hospitalapi.config;

import lk.elevenzcode.healthcare.hospitalapi.web.service.HospitalService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by හShaන් සNදීප on 3/12/2020 1:07 AM
 */
@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(HospitalService.class);
    }
}
