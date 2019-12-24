package vn.hust.edu.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.hust.edu.model.TFTicket;

public interface TFTicketRepository extends PagingAndSortingRepository<TFTicket, Integer>, JpaSpecificationExecutor<TFTicket> {

    TFTicket findById(String id);

    TFTicket findByCode(String code);

    TFTicket save(TFTicket tfTicket);
}
