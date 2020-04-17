package lk.elevenzcode.healthcare.commons.service.impl;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.repository.GenericRepository;
import lk.elevenzcode.healthcare.commons.service.GenericService;
import lk.elevenzcode.healthcare.commons.util.Constant;
import org.glassfish.jersey.internal.guava.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

/**
 * Created by හShaන් සNදීප on 3/21/2020 8:47 PM
 */
@Transactional(value = Constant.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
    rollbackFor = ServiceException.class)
public class GenericServiceImpl<T> implements GenericService<T> {
  private static final Logger LOGGER = LoggerFactory.getLogger(GenericServiceImpl.class);
  @Autowired
  protected MessageSource messageSource;
  private GenericRepository<T> genericRepository;
  private Locale locale = LocaleContextHolder.getLocale();

  protected void init(GenericRepository<T> repo) {
    genericRepository = repo;
  }

  @Override
  public int insert(T domain) throws ServiceException {
    try {
      genericRepository.save(domain);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e.getCause());
    }
    return 0;
  }

  @Override
  public void update(T domain) throws ServiceException {
    try {
      genericRepository.save(domain);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e.getCause());
    }
  }

  @Override
  public T get(Integer id) throws ServiceException {
    try {
      return genericRepository.findById(id).get();
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e.getCause());
    }
  }

  @Override
  public List<T> getAll() throws ServiceException {
    try {
      return Lists.newArrayList(genericRepository.findAll());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e.getCause());
    }
  }

  @Override
  public void delete(T domain) throws ServiceException {
    try {
      genericRepository.delete(domain);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new ServiceException(ServiceException.PROCESSING_FAILURE, e.getMessage(), e.getCause());
    }
  }

  public String getMessage(String msg, String... args) {
    try {
      return messageSource.getMessage(msg, args, locale);
    } catch (NoSuchMessageException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return msg;
  }
}
