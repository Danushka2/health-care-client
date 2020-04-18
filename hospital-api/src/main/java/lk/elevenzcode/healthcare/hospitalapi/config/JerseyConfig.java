package lk.elevenzcode.healthcare.hospitalapi.config;

import lk.elevenzcode.healthcare.hospitalapi.web.service.HospitalRestService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;


@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(HospitalRestService.class);
    }
}
