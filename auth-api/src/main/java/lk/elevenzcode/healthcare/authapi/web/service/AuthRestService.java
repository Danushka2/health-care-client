package lk.elevenzcode.healthcare.authapi.web.service;

import lk.elevenzcode.healthcare.authapi.domain.User;
import lk.elevenzcode.healthcare.authapi.service.UserService;
import lk.elevenzcode.healthcare.authapi.web.dto.UserCreationDto;
import lk.elevenzcode.healthcare.authapi.web.dto.UserInfoDto;
import lk.elevenzcode.healthcare.authapi.web.util.Constant;
import lk.elevenzcode.healthcare.commons.exception.ServiceException;
import lk.elevenzcode.healthcare.commons.web.service.BaseRestService;
import lk.elevenzcode.healthcare.commons.web.service.dto.ServiceResponse;
import lk.elevenzcode.healthcare.commons.web.util.RESTfulUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by හShaන් සNදීප on 4/15/2020 7:35 PM
 */
@Component
@Path(Constant.API_VER + "/" + Constant.API_PATH)
public class AuthRestService extends BaseRestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthRestService.class);

  @Autowired
  private UserService userService;

  @GET
  @Path("/users/{username}")
  @Produces(value = MediaType.TEXT_HTML)
  public Response getByUsername(@PathParam("username") String username) {
    Response response;
    try {
      final User user = userService.getByUsername(username);
      if (user != null) {
        response = RESTfulUtil.getOk(new UserInfoDto(user.getId(), user.getUsername(),
            user.getType().getType(), user.isEnabled()));
      } else {
        response = RESTfulUtil.getNotFound();
      }
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      if (e.getCode() == ServiceException.VALIDATION_FAILURE) {
        response = RESTfulUtil.getNotFound();
      } else {
        response = RESTfulUtil.getInternalServerError();
      }
    }
    return response;
  }

  @POST
  @Path("/users")
  @Produces(value = MediaType.APPLICATION_JSON)
  public Response createUser(UserCreationDto dto) {
    final ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
    try {
      serviceResponse.setBody(userService.createUser(dto));
    } catch (ServiceException e) {
      LOGGER.error(e.getMessage(), e);
      setError(e, serviceResponse);
    }
    return RESTfulUtil.getOk(serviceResponse);
  }
}
