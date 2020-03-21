package lk.elevenzcode.healthcare.appointmentapi.config;

import lk.elevenzcode.healthcare.appointmentapi.web.service.AppointmentRestService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by හShaන් සNදීප on 3/12/2020 1:07 AM
 */
@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(AppointmentRestService.class);
    }
}
