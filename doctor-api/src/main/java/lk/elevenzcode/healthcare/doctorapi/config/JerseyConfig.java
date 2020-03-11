package lk.elevenzcode.healthcare.doctorapi.config;

import lk.elevenzcode.healthcare.doctorapi.web.service.DoctorService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by හShaන් සNදීප on 3/12/2020 1:07 AM
 */
@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(DoctorService.class);
    }
}
