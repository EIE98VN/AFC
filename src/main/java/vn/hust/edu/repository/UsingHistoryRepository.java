package vn.hust.edu.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import vn.hust.edu.model.UsingHistory;

import java.util.List;

public interface UsingHistoryRepository
    extends PagingAndSortingRepository<UsingHistory, Integer>,
        JpaSpecificationExecutor<UsingHistory> {

  /**
   *
   * @param history instance of history
   * @return instance of history found
   */
  UsingHistory save(UsingHistory history);

  /**
   *
   * @param ticketCardId ticket/card id in string form
   * @return instance of using history found
   */
  UsingHistory findByCertificateId(String certificateId);

  /**
   *
   * @param ticketCardId ticket/card id in string form
   * @return list of using history in ascending order of time
   */
  UsingHistory
      findFirstByCertificateIdAndEmbarkationNotNullAndDisembarkationNull(
          String ticketCardId);

  @Query(
      value =
          "SELECT u FROM UsingHistory u WHERE u.certificateId = :certificateId ORDER BY u.checkInAt ASC ")
  List<UsingHistory> findByTicketCardIdFirstTime(
          @Param("certificateId") String certificateId, Pageable pageable);

  UsingHistory findFirstByCertificateId(String certificateId);
}
