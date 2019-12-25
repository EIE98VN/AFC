package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.model.PrepaidCard;
import vn.hust.edu.repository.PrepaidCardRepository;

@Service
public class PrepaidCardService implements CertificateService<PrepaidCard>{

  @Autowired PrepaidCardRepository repository;

  public PrepaidCard findById(String id) {
    return repository.findById(id);
  }

  public PrepaidCard findByCode(String code) {
    return repository.findByCode(code);
  }

  public PrepaidCard save(PrepaidCard prepaidCard) {
    return repository.save(prepaidCard);
  }
}
