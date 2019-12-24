package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.model.Station;
import vn.hust.edu.model.UsingHistory;
import vn.hust.edu.model.support.ResponseBody;

import java.util.Date;

@Service
public class CreateHistoryService {

  @Autowired UsingHistoryService historyService;

  public ResponseBody createCheckInHistory(
      ResponseBody responseBody, String certificateId, Station embarkation) {

    UsingHistory history = new UsingHistory();
    history.setCertificateId(certificateId);
    history.setEmbarkation(embarkation);
    history.setCheckInAt(new Date());
    historyService.save(history);
    responseBody.setHistory(history);

    return responseBody;
  }

  public ResponseBody createCheckOutHistory(
      ResponseBody responseBody, String certificateId, Station disembarkation) {

    UsingHistory history = historyService.findInStation(certificateId);
    history.setDisembarkation(disembarkation);
    history.setCheckOutAt(new Date());
    historyService.save(history);
    responseBody.setHistory(history);

    return responseBody;
  }
}
