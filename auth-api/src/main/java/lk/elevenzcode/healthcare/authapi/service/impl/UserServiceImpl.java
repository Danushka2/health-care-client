package lk.elevenzcode.healthcare.authapi.service.impl;

import lk.elevenzcode.healthcare.authapi.config.security.DefaultPasswordEncoderFactories;
import lk.elevenzcode.healthcare.authapi.domain.User;
import lk.elevenzcode.healthcare.authapi.domain.UserAuthority;
import lk.elevenzcode.healthcare.authapi.domain.UserType;
import lk.elevenzcode.healthcare.authapi.domain.UserTypeAuthority;
import lk.elevenzcode.healthcare.authapi.repository.UserRepository;
import lk.elevenzcode.healthcare.authapi.service.UserService;
import lk.elevenzcode.healthcare.authapi.service.UserTypeAuthorityService;
import lk.elevenzcode.healthcare.authapi.web.dto.UserCreationDto;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.service.impl.GenericServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 * Created by හShaන් සNදීප on 4/15/2020 7:46 PM
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserTypeAuthorityService userTypeAuthorityService;

  @PostConstruct
  void init() {
    init(userRepository);
  }

  @Override
  public User getByUsername(String username) throws ServiceException {
    try {
      return userRepository.findByUsernameAndIsEnabledIsTrue(username);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e.getCause());
    }
  }

  @Override
  public long countByUsername(String username) throws ServiceException {
    try {
      return userRepository.countByUsernameAndIsEnabledIsTrue(username);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e.getCause());
    }
  }

  @Override
  public int createUser(UserCreationDto dto) throws ServiceException {
    final long nUsers = countByUsername(dto.getUsername());
    if (nUsers == 0) {
      final User user = new User();
      user.setUsername(dto.getUsername());
      user.setPassword(DefaultPasswordEncoderFactories.createDelegatingPasswordEncoder()
          .encode(dto.getPassword()));
      user.setEnabled(true);
      user.setType(new UserType(dto.getUserType().getId()));
      final List<UserTypeAuthority> authorities = userTypeAuthorityService.getByType(dto
          .getUserType().getId());
      List<UserAuthority> userAuthorities = new ArrayList<>();
      for (UserTypeAuthority authority : authorities) {
        userAuthorities.add(new UserAuthority(user, authority.getAuthority()));
      }
      user.setAuthorities(userAuthorities);
      insert(user);
      return user.getId();
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE,
          "label.auth.error.username.already.exist");
    }
  }
}
