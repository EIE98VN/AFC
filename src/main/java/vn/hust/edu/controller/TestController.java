package vn.hust.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.hust.edu.model.OWTicket;
import vn.hust.edu.model.support.ResponseBody;
import vn.hust.edu.repository.OWTicketRepository;
import vn.hust.edu.repository.PrepaidCardRepository;
import vn.hust.edu.service.ControlService;

@RestController
public class TestController {

    @Autowired
    PrepaidCardRepository repository;

    @Autowired
    OWTicketRepository owTicketRepository;

    @Autowired
    ControlService controlService;

    @GetMapping("/test")
    public OWTicket test(){
        return owTicketRepository.findByCode("2fe2882c1636c623");
    }

    @RequestMapping(value="/checkIn", method = RequestMethod.GET)
    public ResponseBody checkIn(
            @RequestParam(name = "barCode") String barCode,
            @RequestParam(name = "embarkationLocation") String embarkationLocation) {
        return controlService.checkIn(barCode, embarkationLocation);
    }

    @RequestMapping(value="/checkOut", method = RequestMethod.GET)
    public ResponseBody checkOut(
            @RequestParam(name = "barCode") String barCode,
            @RequestParam(name = "embarkationLocation") String embarkationLocation) {
        return controlService.checkOut(barCode, embarkationLocation);
    }
}
