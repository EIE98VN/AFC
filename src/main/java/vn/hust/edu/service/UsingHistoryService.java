package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.hust.edu.model.UsingHistory;
import vn.hust.edu.repository.UsingHistoryRepository;

import java.util.List;

@Service
public class UsingHistoryService {

  @Autowired UsingHistoryRepository usingHistoryRepository;

  /**
   * @param history an instance of using history
   * @return saved instance of using history
   */
  public UsingHistory save(UsingHistory history) {
    return usingHistoryRepository.save(history);
  }

  /**
   * @param ticketCardId ticket/card id in string form
   * @return instance of using history corresponding to ticket/card id found
   */
  public UsingHistory findFirstByCertificateId(String ticketCardId) {
    return usingHistoryRepository.findFirstByCertificateId(ticketCardId);
  }

  /**
   * @param ticketCardId ticket/card id in string form
   * @return instance of using history
   */
  public UsingHistory findInStation(String ticketCardId) {
    return usingHistoryRepository
        .findFirstByCertificateIdAndEmbarkationNotNullAndDisembarkationNull(
            ticketCardId);
  }

  /**
   * @param ticketCard ticket/card id in string form
   * @return instance of using history
   */
  public UsingHistory findInStationFirstTime(String ticketCardId) {
    List<UsingHistory> usingHistoryList =
        usingHistoryRepository.findByTicketCardIdFirstTime(ticketCardId, PageRequest.of(0, 1));
    if (usingHistoryList.size() == 0) return null;
    return usingHistoryList.get(0);
  }
}
