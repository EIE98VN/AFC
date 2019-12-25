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
import java.util.ArrayList;
import java.util.Date;
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
  public ResponseBody checkInResponse(Station embarkation) {
    if (isCardInsufficient()) {
      return GeneralUtil.createResponse(Status.FAIL, this, Type.CARD, Message.INSUFFICIENT_CARD);
    }
    return GeneralUtil.createResponse(Status.SUCCESS, this, Type.CARD, Message.SUCCESSFUL_CHECKIN);
  }

  @Override
  public ResponseBody checkOutResponse(Station disembarkation) {
    List<UsageHistory> usageHistories = new ArrayList<UsageHistory>(this.getUsageHistories());
    int historyLength = usageHistories.size();
    FareCalculate fareCalculate = new InLineFareCalculate();
    float fare = fareCalculate.calculate(usageHistories.get(historyLength-1).getEmbarkation(), disembarkation);
    System.out.println("FARE: "+ fare);
    if (fare > this.balance){
      return GeneralUtil.createResponse(Status.FAIL, this, Type.CARD, Message.INSUFFICIENT_CARD);
    }
    this.decreaseBalance(fare);
    return GeneralUtil.createResponse(Status.SUCCESS, this, Type.CARD, Message.SUCCESSFUL_CHECKOUT);
  }

  private boolean isCardInsufficient() {
    return this.balance < Fare.BASE_FARE;
  }
}
