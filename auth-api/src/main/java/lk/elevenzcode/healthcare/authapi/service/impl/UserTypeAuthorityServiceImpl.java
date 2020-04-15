package lk.elevenzcode.healthcare.authapi.service.impl;

import lk.elevenzcode.healthcare.authapi.domain.UserTypeAuthority;
import lk.elevenzcode.healthcare.authapi.repository.UserTypeAuthorityRepository;
import lk.elevenzcode.healthcare.authapi.service.UserTypeAuthorityService;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.PostConstruct;

/**
 * Created by හShaන් සNදීප on 4/15/2020 10:04 PM
 */
@Service
public class UserTypeAuthorityServiceImpl extends GenericServiceImpl<UserTypeAuthority>
    implements UserTypeAuthorityService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserTypeAuthorityServiceImpl.class);

  @Autowired
  private UserTypeAuthorityRepository userTypeAuthorityRepository;

  @PostConstruct
  void init() {
    init(userTypeAuthorityRepository);
  }

  @Override
  public List<UserTypeAuthority> getByType(int type) throws ServiceException {
    try {
      return userTypeAuthorityRepository.findAllByTypeId(type);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e.getCause());
    }
  }
}
