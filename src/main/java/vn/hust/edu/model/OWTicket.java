package vn.hust.edu.model;

import vn.hust.edu.FareCalculate;
import vn.hust.edu.GeneralUtil;
import vn.hust.edu.InLineFareCalculate;
import vn.hust.edu.constant.Message;
import vn.hust.edu.constant.Status;
import vn.hust.edu.constant.Type;
import vn.hust.edu.model.support.ResponseBody;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "oneway_ticket",
    indexes = {@Index(name = "idx_code", columnList = "code", unique = true)})
public class OWTicket extends Certificate {

  @ManyToOne
  @JoinColumn(name = "embarkation_id", foreignKey = @ForeignKey(name = "FK_TICKET_EMBARKATION"))
  private Station embarkation;

  @ManyToOne
  @JoinColumn(
      name = "disembarkation_id",
      foreignKey = @ForeignKey(name = "FK_TICKET_DISEMBARKATION"))
  private Station disembarkation;

  @Column(name = "fare")
  private float fare;

  public Station getEmbarkation() {
    return embarkation;
  }

  public void setEmbarkation(Station embarkation) {
    this.embarkation = embarkation;
  }

  public Station getDisembarkation() {
    return disembarkation;
  }

  public void setDisembarkation(Station disembarkation) {
    this.disembarkation = disembarkation;
  }

  public float getFare() {
    return fare;
  }

  public void setFare(float fare) {
    this.fare = fare;
  }

  @Override
  public ResponseBody checkInResponse(Station embarkation, Line line) {
    if (isUsed())
      return GeneralUtil.createResponse(
          Status.FAIL, this, Type.TICKET_ONEWAY, Message.ALREADY_USED);
    if (!isValidEmbarkation(embarkation, line))
      return GeneralUtil.createResponse(
          Status.FAIL, this, Type.TICKET_ONEWAY, Message.INVALID_EMBARKATION);
    return GeneralUtil.createResponse(
        Status.SUCCESS, this, Type.TICKET_ONEWAY, Message.SUCCESSFUL_CHECKIN);
  }

  @Override
  public ResponseBody checkOutResponse(Station disembarkation, Line line) {
    List<UsageHistory> usageHistories = new ArrayList<UsageHistory>(this.getUsageHistories());
    FareCalculate fareCalculate = new InLineFareCalculate();
    float fare = fareCalculate.calculate(usageHistories.get(0).getEmbarkation(), disembarkation, line);
    if (fare > this.fare)
      return GeneralUtil.createResponse(
          Status.FAIL, this, Type.TICKET_ONEWAY, Message.INSUFFICIENT_ONEWAY_TICKET);
    return GeneralUtil.createResponse(
        Status.SUCCESS, this, Type.TICKET_ONEWAY, Message.SUCCESSFUL_CHECKOUT);
  }

  private boolean isValidEmbarkation(Station embarkation, Line line) {

    Distance embarkationStationDistance = embarkation.findByLineId(line.getId());
    Distance leftStationDistance = this.embarkation.findByLineId(line.getId());
    Distance rightStationDistance = this.disembarkation.findByLineId(line.getId());

    if (embarkationStationDistance == null
        || leftStationDistance == null
        || rightStationDistance == null) return false;

    float embarkationDistance = embarkationStationDistance.getDistance();
    float leftLimit = leftStationDistance.getDistance();
    float rightLimit = rightStationDistance.getDistance();

    return !((embarkationDistance > leftLimit && embarkationDistance > rightLimit)
        || (embarkationDistance < leftLimit && embarkationDistance < rightLimit));
  }

  private boolean isUsed() {
    return this.getUsageHistories().size() != 0;
  }
}
