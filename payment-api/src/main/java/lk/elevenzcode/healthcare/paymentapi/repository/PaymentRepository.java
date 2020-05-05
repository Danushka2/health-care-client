package lk.elevenzcode.healthcare.paymentapi.repository;

import lk.elevenzcode.healthcare.commons.repository.GenericRepository;
import lk.elevenzcode.healthcare.paymentapi.domain.Payment;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 3/21/2020 10:45 PM
 */
public interface PaymentRepository extends GenericRepository<Payment> {
  Payment findByAppointmentId(int appointmentId);

  long countAllByStatus_NameContainingIgnoreCaseOrReferenceContainingIgnoreCase(String status,
                                                                                String ref);

  List<Payment> getAllByStatus_NameContainingIgnoreCaseOrReferenceContainingIgnoreCase(String status,
                                                                                       String ref,
                                                                                       Pageable pageable);
}
