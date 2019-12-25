package vn.hust.edu.service;

import hust.soict.se.gate.Gate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.GeneralUtil;
import vn.hust.edu.constant.Message;
import vn.hust.edu.constant.Status;
import vn.hust.edu.constant.Type;
import vn.hust.edu.model.Certificate;
import vn.hust.edu.model.PrepaidCard;
import vn.hust.edu.model.Station;
import vn.hust.edu.model.UsageHistory;
import vn.hust.edu.model.support.ResponseBody;

@Service
public class ControlService {

  @Autowired UsingHistoryService historyService;

  @Autowired StationService stationService;

  @Autowired PrepaidCardService prepaidCardService;

  @Autowired GetPaymentTypeService getPaymentTypeService;

  @Autowired CreateHistoryService createHistoryService;

  public ResponseBody checkIn(String barCode, String location) {

//    Gate gate = Gate.getInstance();

    Station embarkation = stationService.findByLocation(location);

    if (embarkation == null)
      return GeneralUtil.createResponse(Status.FAIL, Message.LOCATION_NOT_FOUND, Type.EMBARKATION);

    Certificate certificate = getPaymentTypeService.getPaymentType(barCode);

    if (certificate == null)
      return GeneralUtil.createResponse(
          Status.FAIL, null, Type.CERTIFICATE, Message.INVALID_CERTIFICATE);

    if (isInStation(certificate.getId()))
      return GeneralUtil.createResponse(
          Status.FAIL, certificate, Type.CERTIFICATE, Message.ALREADY_CHECKIN);

    ResponseBody responseBody = certificate.checkInResponse(embarkation);

    if (responseBody.getStatus() == 1) {
      responseBody =
          createHistoryService.createCheckInHistory(responseBody, certificate, embarkation);
//      gate.open();
    }

    return responseBody;
  }

  public ResponseBody checkOut(String barCode, String location) {

//    Gate gate = Gate.getInstance();

    Station disembarkation = stationService.findByLocation(location);

    if (disembarkation == null)
      return GeneralUtil.createResponse(
          Status.FAIL, Message.LOCATION_NOT_FOUND, Type.DISEMBARKATION);

    Certificate certificate = getPaymentTypeService.getPaymentType(barCode);

    if (certificate == null)
      return GeneralUtil.createResponse(
          Status.FAIL, null, Type.CERTIFICATE, Message.INVALID_CERTIFICATE);

    if (!isInStation(certificate.getId()))
      return GeneralUtil.createResponse(
          Status.FAIL, certificate, Type.CERTIFICATE, Message.NOT_CHECKIN);

    ResponseBody responseBody = certificate.checkOutResponse(disembarkation);

    if (responseBody.getStatus() == 1) {
      if (certificate instanceof PrepaidCard) {
        prepaidCardService.save((PrepaidCard) responseBody.getData());
      }
      responseBody =
          createHistoryService.createCheckOutHistory(
              responseBody, certificate, disembarkation);

//      gate.open();
    }

    return responseBody;
  }

  private boolean isInStation(String cardTicketId) {
    UsageHistory history = historyService.findInStation(cardTicketId);
    if (history != null) return true;
    return false;
  }
}
