package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.model.OWTicket;
import vn.hust.edu.repository.OWTicketRepository;

import javax.transaction.Transactional;

@Service
public class OWTicketService implements CertificateService<OWTicket>{

  @Autowired OWTicketRepository repository;

  public OWTicket findById(String id) {
    return repository.findById(id);
  }

  public OWTicket findByCode(String code) {
    return repository.findByCode(code);
  }

  @Transactional
  public OWTicket save(OWTicket owTicket) {
    return repository.save(owTicket);
  }
}
