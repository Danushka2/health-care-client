package lk.elevenzcode.healthcare.paymentapi.service;

import lk.elevenzcode.healthcare.commons.dto.ResultAdditionalData;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.GenericService;
import lk.elevenzcode.healthcare.paymentapi.domain.Payment;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:35 PM
 */
public interface PaymentService extends GenericService<Payment> {
  Payment getByAppointmentId(int apptId) throws ServiceException;

  int save(int apptId, String paymentIntentId) throws ServiceException;

  void refundByAppt(int apptId, String reason) throws ServiceException;

  void refund(int id, String reason) throws ServiceException;

  List<Payment> getList(Integer offset, Integer limit, String sort, String order,
                        ResultAdditionalData additionalData) throws ServiceException;

  void update(int id, String paymentIntentId) throws ServiceException;
}
