package lk.elevenzcode.healthcare.authapi.repository;

import lk.elevenzcode.healthcare.authapi.domain.User;
import lk.elevenzcode.healthcare.commons.repository.GenericRepository;

/**
 * Created by හShaන් සNදීප on 4/15/2020 7:47 PM
 */
public interface UserRepository extends GenericRepository<User> {
  User findByUsernameAndIsEnabledIsTrue(String username);

  long countByUsernameAndIsEnabledIsTrue(String username);
}
