package vn.hust.edu.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.hust.edu.model.UsageHistory;

public interface UsageHistoryRepository
    extends PagingAndSortingRepository<UsageHistory, Integer>,
        JpaSpecificationExecutor<UsageHistory> {

  /**
   *
   * @param history instance of history
   * @return instance of history found
   */
  UsageHistory save(UsageHistory history);

  /**
   *
   * @param ticketCardId ticket/card id in string form
   * @return list of using history in ascending order of time
   */
  UsageHistory
      findFirstByCertificateIdAndEmbarkationNotNullAndDisembarkationNull(
          String certificateId);

  UsageHistory findFirstByCertificateId(String certificateId);
}
