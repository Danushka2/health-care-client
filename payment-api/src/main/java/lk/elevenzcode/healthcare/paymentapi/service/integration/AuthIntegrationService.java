package lk.elevenzcode.healthcare.paymentapi.service.integration;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.paymentapi.service.integration.dto.UserInfo;

/**
 * Created by හShaන් සNදීප on 4/16/2020 9:03 AM
 */
public interface AuthIntegrationService {
  UserInfo getByUsername(String username) throws ServiceException;
}
