package lk.elevenzcode.healthcare.commons.web.util;

import javax.ws.rs.core.Response;

/**
 * Created by හShaන් සNදීප on 3/21/2020 9:19 PM
 */
public class RESTfulUtil {
  /*
  Construct response with HTTP 200
   */
  public static Response getOk(Object ob) {
    return Response.status(Response.Status.OK).entity(ob).build();
  }

  /*
  Construct response with HTTP 200
   */
  public static Response getOk() {
    return Response.status(Response.Status.OK).build();
  }

  /*
  Construct response with HTTP 201
   */
  public static Response getCreated(Object ob) {
    return Response.status(Response.Status.CREATED).entity(ob).build();
  }

  /*
  Construct response with HTTP 400
   */
  public static Response getBadRequest() {
    return Response.status(Response.Status.BAD_REQUEST).build();
  }

  /*
  Construct response with HTTP 403
   */
  public static Response getForbidden() {
    return Response.status(Response.Status.FORBIDDEN).build();
  }

  /*
  Construct response with HTTP 404
   */
  public static Response getNotFound() {
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  /*
  Construct response with HTTP 500
   */
  public static Response getInternalServerError() {
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
  }
}
