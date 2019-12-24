package vn.hust.edu.model;

import vn.hust.edu.GeneralUtil;
import vn.hust.edu.constant.Message;
import vn.hust.edu.constant.Status;
import vn.hust.edu.constant.Type;
import vn.hust.edu.model.support.ResponseBody;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(
    name = "tf_ticket",
    indexes = {@Index(name = "idx_code", columnList = "code", unique = true)})
public class TFTicket extends Certificate {

  @Override
  public ResponseBody checkInResponse(Station embarkation, UsingHistory history) {
    if (isTicketExpired(history))
      GeneralUtil.createResponse(Status.FAIL, this, Type.TICKET_24H, Message.EXPIRED_24H_TICKET);
    return GeneralUtil.createResponse(
        Status.FAIL, this, Type.TICKET_24H, Message.SUCCESSFUL_CHECKIN);
  }

  @Override
  public ResponseBody checkOutResponse(Station disembarkation, UsingHistory history) {
    return GeneralUtil.createResponse(Status.SUCCESS, this, Type.TICKET_24H, Message.SUCCESSFUL_CHECKOUT);
  }

  private boolean isTicketExpired(UsingHistory history) {
    if (history == null) return false;
    Date now = new Date();
    Date expiredDate = new Date(history.getCheckInAt().getTime() + 24 * 3600 * 1000);
    return now.after(expiredDate);
  }
}
