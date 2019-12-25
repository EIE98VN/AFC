package vn.hust.edu.model;

import vn.hust.edu.GeneralUtil;
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
    name = "tf_ticket",
    indexes = {@Index(name = "idx_code", columnList = "code", unique = true)})
public class TFTicket extends Certificate {

  @Column(name = "fare")
  private float fare;

  public float getFare() {
    return fare;
  }

  @Override
  public ResponseBody checkInResponse(Station embarkation, Line line) {
    if (isTicketExpired())
      GeneralUtil.createResponse(Status.FAIL, this, Type.TICKET_24H, Message.EXPIRED_24H_TICKET);
    return GeneralUtil.createResponse(
        Status.FAIL, this, Type.TICKET_24H, Message.SUCCESSFUL_CHECKIN);
  }

  @Override
  public ResponseBody checkOutResponse(Station disembarkation, Line line) {
    return GeneralUtil.createResponse(
        Status.SUCCESS, this, Type.TICKET_24H, Message.SUCCESSFUL_CHECKOUT);
  }

  private boolean isTicketExpired() {
    List<UsageHistory> usageHistories = new ArrayList<UsageHistory>(this.getUsageHistories());
    if (usageHistories.size() == 0) return false;
    Date now = new Date();
    Date expiredDate = new Date(usageHistories.get(0).getCheckInAt().getTime() + 24 * 3600 * 1000);
    return now.after(expiredDate);
  }
}
