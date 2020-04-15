package lk.elevenzcode.healthcare.authapi.service;

import lk.elevenzcode.healthcare.authapi.domain.UserTypeAuthority;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.GenericService;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 4/15/2020 10:04 PM
 */
public interface UserTypeAuthorityService extends GenericService<UserTypeAuthority> {
  List<UserTypeAuthority> getByType(int type) throws ServiceException;
}
