package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.hust.edu.model.UsageHistory;
import vn.hust.edu.repository.UsingHistoryRepository;

import java.util.List;

@Service
public class UsingHistoryService {

  @Autowired UsingHistoryRepository usingHistoryRepository;

  /**
   * @param history an instance of using history
   * @return saved instance of using history
   */
  public UsageHistory save(UsageHistory history) {
    return usingHistoryRepository.save(history);
  }

  /**
   * @param ticketCardId ticket/card id in string form
   * @return instance of using history corresponding to ticket/card id found
   */
  public UsageHistory findFirstByCertificateId(String ticketCardId) {
    return usingHistoryRepository.findFirstByCertificateId(ticketCardId);
  }

  /**
   * @param ticketCardId ticket/card id in string form
   * @return instance of using history
   */
  public UsageHistory findInStation(String certificateId) {
    return usingHistoryRepository
        .findFirstByCertificateIdAndEmbarkationNotNullAndDisembarkationNull(
            certificateId);
  }
}
