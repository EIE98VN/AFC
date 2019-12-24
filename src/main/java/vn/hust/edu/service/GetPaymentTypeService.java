package vn.hust.edu.service;

import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.recognizer.TicketRecognizer;
import hust.soict.se.scanner.CardScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.GeneralUtil;
import vn.hust.edu.model.Certificate;
import vn.hust.edu.model.OWTicket;
import vn.hust.edu.model.PrepaidCard;
import vn.hust.edu.model.TFTicket;

@Service
public class GetPaymentTypeService {

  @Autowired PrepaidCardService prepaidCardService;

  @Autowired TFTicketService tfTicketService;

  @Autowired OWTicketService owTicketService;

  public Certificate getPaymentType(String barCode) {
    try {
      if (GeneralUtil.isCard(barCode)) {

        CardScanner cardScanner = CardScanner.getInstance();
        String cardCode = cardScanner.process(barCode);
        PrepaidCard card = prepaidCardService.findByCode(cardCode);

        return card;
      } else {
        TicketRecognizer ticketRecognizer = TicketRecognizer.getInstance();
        String ticketCode = ticketRecognizer.process(barCode);

        OWTicket owTicket = owTicketService.findByCode(ticketCode);
        if (owTicket != null) {
          return owTicket;
        } else {
          TFTicket tfTicket = tfTicketService.findByCode(ticketCode);
          return tfTicket;
        }
      }
    } catch (InvalidIDException e) {
      e.printStackTrace();
      return null;
    }
  }
}
