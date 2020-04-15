package lk.elevenzcode.healthcare.authapi.repository;

import lk.elevenzcode.healthcare.authapi.domain.UserTypeAuthority;
import lk.elevenzcode.healthcare.commons.repository.GenericRepository;

import java.util.List;

/**
 * Created by හShaන් සNදීප on 4/15/2020 10:05 PM
 */
public interface UserTypeAuthorityRepository extends GenericRepository<UserTypeAuthority> {
    List<UserTypeAuthority> findAllByTypeId(int type);
}
