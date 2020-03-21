package lk.elevenzcode.healthcare.commons.service;

import lk.elevenzcode.healthcare.commons.exception.ServiceException;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 3/21/2020 8:40 PM
 */
public interface GenericService<T> {
  void insert(T domain) throws ServiceException;

  void update(T domain) throws ServiceException;

  T get(Integer id) throws ServiceException;

  List<T> getAll() throws ServiceException;

  void delete(T domain) throws ServiceException;
}
