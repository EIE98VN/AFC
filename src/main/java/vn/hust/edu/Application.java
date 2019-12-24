package vn.hust.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.hust.edu.model.PrepaidCard;
import vn.hust.edu.repository.PrepaidCardRepository;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
  }
}
