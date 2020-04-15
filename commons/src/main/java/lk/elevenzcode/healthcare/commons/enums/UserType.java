package lk.elevenzcode.healthcare.commons.enums;

/**
 * Created by හShaන් සNදීප on 4/15/2020 8:48 PM
 */
public enum UserType {
  SYSTEM_ADMIN(1), HOSPITAL_ADMIN(2), DOCTOR(3), PATIENT(4);

  private int id;

  UserType(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
