package vn.hust.edu.model;

import vn.hust.edu.service.FareCalculate;
import vn.hust.edu.GeneralUtil;
import vn.hust.edu.service.InLineFareCalculate;
import vn.hust.edu.constant.Fare;
import vn.hust.edu.constant.Message;
import vn.hust.edu.constant.Status;
import vn.hust.edu.constant.Type;
import vn.hust.edu.model.support.ResponseBody;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "prepaid_card",
    indexes = {@Index(name = "idx_code", columnList = "code", unique = true)})
public class PrepaidCard extends Certificate {

  @Column(name = "balance")
  private float balance;

  public float getBalance() {
    return balance;
  }

  public void decreaseBalance(float fare) {
    this.balance = this.balance - fare;
  }

  @Override
  public ResponseBody checkInResponse(Station embarkation, Line line) {
    if (isCardInsufficient()) {
      return GeneralUtil.createResponse(Status.FAIL, this, Type.CARD, Message.INSUFFICIENT_CARD);
    }
    return GeneralUtil.createResponse(Status.SUCCESS, this, Type.CARD, Message.SUCCESSFUL_CHECKIN);
  }

  @Override
  public ResponseBody checkOutResponse(Station disembarkation, Line line) {

    List<UsageHistory> usageHistories = new ArrayList<UsageHistory>(this.getUsageHistories());
    int historyLength = usageHistories.size();
    UsageHistory latestUsage = usageHistories.get(historyLength - 1);
    Distance embarkationDistance = latestUsage.getEmbarkation().findByLineId(line.getId());

    if (embarkationDistance == null)
      return GeneralUtil.createResponse(Status.FAIL, Message.NOT_ON_SAME_LINE, Type.LINE);
    FareCalculate fareCalculate = new InLineFareCalculate();
    float fare =
        fareCalculate.calculate(
            usageHistories.get(historyLength - 1).getEmbarkation(), disembarkation, line);
    if (fare > this.balance) {
      return GeneralUtil.createResponse(Status.FAIL, this, Type.CARD, Message.INSUFFICIENT_CARD);
    }
    this.decreaseBalance(fare);
    return GeneralUtil.createResponse(Status.SUCCESS, this, Type.CARD, Message.SUCCESSFUL_CHECKOUT);
  }

  /**
   * Check if card has enough money or not
   *
   * @return true if card is insufficient, false otherwise
   */
  private boolean isCardInsufficient() {
    return this.balance < Fare.BASE_FARE;
  }

}
