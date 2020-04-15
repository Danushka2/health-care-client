package lk.elevenzcode.healthcare.authapi.service;

import lk.elevenzcode.healthcare.authapi.domain.User;
import lk.elevenzcode.healthcare.authapi.web.dto.UserCreationDto;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.GenericService;

/**
 * Created by හShaන් සNදීප on 4/15/2020 7:46 PM
 */
public interface UserService extends GenericService<User> {
  User getByUsername(String username) throws ServiceException;

  long countByUsername(String username) throws ServiceException;

  int createUser(UserCreationDto dto) throws ServiceException;
}
