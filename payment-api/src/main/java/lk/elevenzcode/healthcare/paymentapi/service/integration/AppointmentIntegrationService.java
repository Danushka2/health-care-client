package lk.elevenzcode.healthcare.paymentapi.service.integration;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.paymentapi.service.integration.dto.AppointmentInfo;
import lk.elevenzcode.healthcare.paymentapi.service.integration.dto.AppointmentUpdateReq;

/**
 * Created by හShaන් සNදීප on 3/27/2020 3:58 PM
 */
public interface AppointmentIntegrationService {
  AppointmentInfo getByApptId(int apptId) throws ServiceException;

  void updateStatus(int apptId, AppointmentUpdateReq req) throws ServiceException;
}
