package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.model.PrepaidCard;
import vn.hust.edu.repository.PrepaidCardRepository;

@Service
public class PrepaidCardService {

  @Autowired PrepaidCardRepository repository;

  PrepaidCard findById(String id) {
    return repository.findById(id);
  }

  PrepaidCard findByCode(String code) {
    return repository.findByCode(code);
  }

  PrepaidCard save(PrepaidCard prepaidCard) {
    return repository.save(prepaidCard);
  }
}
