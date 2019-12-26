package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.model.Certificate;
import vn.hust.edu.model.Station;
import vn.hust.edu.model.UsageHistory;
import vn.hust.edu.model.support.ResponseBody;

import java.util.Date;

@Service
public class CreateHistoryService {

  @Autowired
  UsageHistoryService historyService;

  /**
   *
   * @param responseBody
   * @param certificate
   * @param embarkation
   * @return
   */
  public ResponseBody createCheckInHistory(
          ResponseBody responseBody, Certificate certificate, Station embarkation) {

    UsageHistory history = new UsageHistory();
    history.setCertificate(certificate);
    history.setEmbarkation(embarkation);
    history.setCheckInAt(new Date());
    historyService.save(history);
    responseBody.setHistory(history);

    return responseBody;
  }

  /**
   *
   * @param responseBody
   * @param certificate
   * @param disembarkation
   * @return
   */
  public ResponseBody createCheckOutHistory(
      ResponseBody responseBody, Certificate certificate, Station disembarkation) {

    UsageHistory history = historyService.findInStation(certificate.getId());
    history.setDisembarkation(disembarkation);
    history.setCheckOutAt(new Date());
    historyService.save(history);
    responseBody.setHistory(history);

    return responseBody;
  }
}
