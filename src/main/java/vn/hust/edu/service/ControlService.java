package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.GeneralUtil;
import vn.hust.edu.constant.Message;
import vn.hust.edu.constant.Status;
import vn.hust.edu.constant.Type;
import vn.hust.edu.model.*;
import vn.hust.edu.model.support.ResponseBody;

@Service
public class ControlService {

  @Autowired UsageHistoryService historyService;

  @Autowired StationService stationService;

  @Autowired PrepaidCardService prepaidCardService;

  @Autowired GetCertificateTypeService getCertificateTypeService;

  @Autowired CreateHistoryService createHistoryService;

  @Autowired LineService lineService;

  /**
   * @param barCode barCode from ticket/card in string form
   * @param location station location in string form
   * @param lineName name of line that is checked in
   * @return status of the check-in in the form of ResponseBody
   */
  public ResponseBody checkIn(String barCode, String location, String lineName) {

    //    Gate gate = Gate.getInstance();

    Station embarkation = stationService.findByLocation(location);

    if (embarkation == null)
      return GeneralUtil.createResponse(Status.FAIL, Message.LOCATION_NOT_FOUND, Type.EMBARKATION);

    Line line = lineService.findByName(lineName);

    if (line == null)
      return GeneralUtil.createResponse(Status.FAIL, Message.LINE_NOT_FOUND, Type.LINE);

    Certificate certificate = getCertificateTypeService.getCertificateType(barCode);

    if (certificate == null)
      return GeneralUtil.createResponse(
          Status.FAIL, null, Type.CERTIFICATE, Message.INVALID_CERTIFICATE);

    if (isInStation(certificate.getId()))
      return GeneralUtil.createResponse(
          Status.FAIL, certificate, Type.CERTIFICATE, Message.ALREADY_CHECKIN);

    ResponseBody responseBody = certificate.checkInResponse(embarkation, line);

    if (responseBody.getStatus() == 1) {
      responseBody =
          createHistoryService.createCheckInHistory(responseBody, certificate, embarkation);
      //      gate.open();
    }

    return responseBody;
  }

  /**
   * @param barCode barCode from ticket/card in string form
   * @param location station location in string form
   * @param lineName name of line that is checked out
   * @return status of the check-in in the form of ResponseBody
   */
  public ResponseBody checkOut(String barCode, String location, String lineName) {

    //    Gate gate = Gate.getInstance();

    Station disembarkation = stationService.findByLocation(location);

    if (disembarkation == null)
      return GeneralUtil.createResponse(
          Status.FAIL, Message.LOCATION_NOT_FOUND, Type.DISEMBARKATION);

    Line line = lineService.findByName(lineName);

    if (line == null)
      return GeneralUtil.createResponse(Status.FAIL, Message.LINE_NOT_FOUND, Type.LINE);

    Certificate certificate = getCertificateTypeService.getCertificateType(barCode);

    if (certificate == null)
      return GeneralUtil.createResponse(
          Status.FAIL, null, Type.CERTIFICATE, Message.INVALID_CERTIFICATE);

    if (!isInStation(certificate.getId()))
      return GeneralUtil.createResponse(
          Status.FAIL, certificate, Type.CERTIFICATE, Message.NOT_CHECKIN);

    ResponseBody responseBody = certificate.checkOutResponse(disembarkation, line);

    if (responseBody.getStatus() == 1) {
      if (certificate instanceof PrepaidCard) {
        prepaidCardService.save((PrepaidCard) responseBody.getData());
      }
      responseBody =
          createHistoryService.createCheckOutHistory(responseBody, certificate, disembarkation);

      //      gate.open();
    }

    return responseBody;
  }

  /**
   * @param certificateId ticket/card id in string form
   * @return status whether card is currently checked-in or not in boolean
   */
  private boolean isInStation(String certificateId) {
    UsageHistory history = historyService.findInStation(certificateId);
    if (history != null) return true;
    return false;
  }
}
