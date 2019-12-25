package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.model.TFTicket;
import vn.hust.edu.repository.TFTicketRepository;

@Service
public class TFTicketService implements CertificateService<TFTicket>{

  @Autowired TFTicketRepository repository;

  public TFTicket findById(String id) {
    return repository.findById(id);
  }

  public TFTicket findByCode(String code) {
    return repository.findByCode(code);
  }

  public TFTicket save(TFTicket tfTicket) {
    return repository.save(tfTicket);
  }

}
