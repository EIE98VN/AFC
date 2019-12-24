package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.model.TFTicket;
import vn.hust.edu.repository.TFTicketRepository;

@Service
public class TFTicketService {

  @Autowired TFTicketRepository repository;

  TFTicket findById(String id) {
    return repository.findById(id);
  }

  TFTicket findByCode(String code) {
    return repository.findByCode(code);
  }

  TFTicket save(TFTicket tfTicket) {
    return repository.save(tfTicket);
  }
}
