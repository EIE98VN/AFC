package vn.hust.edu.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.hust.edu.model.OWTicket;

public interface OWTicketRepository extends PagingAndSortingRepository<OWTicket, Integer>, JpaSpecificationExecutor<OWTicket> {

    OWTicket findById(String id);

    OWTicket findByCode(String code);

    OWTicket save(OWTicket owTicket);
}
