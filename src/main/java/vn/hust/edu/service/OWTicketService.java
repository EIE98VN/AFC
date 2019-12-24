package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.model.OWTicket;
import vn.hust.edu.repository.OWTicketRepository;

import javax.transaction.Transactional;

@Service
public class OWTicketService {

  @Autowired OWTicketRepository repository;

  OWTicket findByID(String id) {
    return repository.findById(id);
  }

  OWTicket findByCode(String code) {
    return repository.findByCode(code);
  }

  @Transactional
  OWTicket save(OWTicket owTicket) {
    return repository.save(owTicket);
  }
}
