package lk.elevenzcode.healthcare.hospitalapi.domain;

import lk.elevenzcode.healthcare.commons.domain.BaseDomain;
import lk.elevenzcode.healthcare.commons.util.Constant;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

@Entity
@Table(name = HospitalRoom.TABLE_NAME)
public class HospitalRoom extends BaseDomain {
  public static final String TABLE_NAME = "hospital_room";
  public static final short STATUS_AVAILABLE = 1;
  public static final short STATUS_IN_REPAIR = 2;
  public static final short STATUS_DELETED = 3;

  @ManyToOne
  @JoinColumn(name = "hospital_id", nullable = false)
  private Hospital hospital;

  @Column(name = "room_no", nullable = false, length = 10)
  private String roomNo;

  @Column(name = "location", length = 50)
  private String location;

  @Column(name = "free", nullable = false)
  @Digits(integer = Constant.DEFAULT_MONEY_INT, fraction = Constant.DEFAULT_MONEY_FRAC)
  private BigDecimal fee;

  @Column(name = "status", nullable = false)
  private short status = STATUS_AVAILABLE;

  public Hospital getHospital() {
    return hospital;
  }

  public void setHospital(Hospital hospital) {
    this.hospital = hospital;
  }

  public String getRoomNo() {
    return roomNo;
  }

  public void setRoomNo(String roomNo) {
    this.roomNo = roomNo;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public BigDecimal getFee() {
    return fee;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }

  public short getStatus() {
    return status;
  }

  public void setStatus(short status) {
    this.status = status;
  }
}
