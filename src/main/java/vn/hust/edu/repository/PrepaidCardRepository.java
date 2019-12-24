package vn.hust.edu.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.hust.edu.model.PrepaidCard;

public interface PrepaidCardRepository extends PagingAndSortingRepository<PrepaidCard, Integer>, JpaSpecificationExecutor<PrepaidCard> {

    PrepaidCard findById(String id);

    PrepaidCard findByCode(String code);

    PrepaidCard save(PrepaidCard prepaidCard);
}
