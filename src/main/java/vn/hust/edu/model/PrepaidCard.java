package vn.hust.edu.model;

import vn.hust.edu.FareCalculate;
import vn.hust.edu.GeneralUtil;
import vn.hust.edu.InLineFareCalculate;
import vn.hust.edu.constant.Fare;
import vn.hust.edu.constant.Message;
import vn.hust.edu.constant.Status;
import vn.hust.edu.constant.Type;
import vn.hust.edu.model.support.ResponseBody;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(
    name = "card",
    indexes = {@Index(name = "idx_code", columnList = "code", unique = true)})
public class PrepaidCard extends Certificate {

  @Column(name = "activated_at")
  private Date activatedAt;

  @Column(name = "expired_at")
  private Date expiredAt;

  @Column(name = "balance")
  private float balance;

  public Date getActivatedAt() {
    return activatedAt;
  }

  public void setActivatedAt(Date activatedAt) {
    this.activatedAt = activatedAt;
  }

  public Date getExpiredAt() {
    return expiredAt;
  }

  public void setExpiredAt(Date expiredAt) {
    this.expiredAt = expiredAt;
  }

  public float getBalance() {
    return balance;
  }

  public void decreaseBalance(float balance) {
    this.balance -= balance;
  }

  @Override
  public ResponseBody checkInResponse(Station embarkation, UsingHistory history) {

    if (isCardInsufficient()) {
      return GeneralUtil.createResponse(Status.FAIL, this, Type.CARD, Message.INSUFFICIENT_CARD);
    }
    return GeneralUtil.createResponse(Status.SUCCESS, this, Type.CARD, Message.SUCCESSFUL_CHECKIN);
  }

  @Override
  public ResponseBody checkOutResponse(Station disembarkation, UsingHistory history) {
    FareCalculate fareCalculate = new InLineFareCalculate();
    float fare = fareCalculate.calculate(history.getEmbarkation(), disembarkation);
    if (fare > this.balance)
      return GeneralUtil.createResponse(
              Status.FAIL, this, Type.CARD, Message.INSUFFICIENT_CARD);
    return GeneralUtil.createResponse(
            Status.SUCCESS, this, Type.CARD, Message.SUCCESSFUL_CHECKOUT);
  }

  private boolean isCardInsufficient() {
    return this.balance < Fare.BASE_FARE;
  }
}
