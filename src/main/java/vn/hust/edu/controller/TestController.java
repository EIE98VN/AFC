package vn.hust.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.hust.edu.model.support.ResponseBody;
import vn.hust.edu.repository.OWTicketRepository;
import vn.hust.edu.repository.PrepaidCardRepository;
import vn.hust.edu.service.ControlService;

@RestController
public class TestController {

  @Autowired PrepaidCardRepository repository;

  @Autowired OWTicketRepository owTicketRepository;

  @Autowired ControlService controlService;

  @RequestMapping(value = "/checkIn", method = RequestMethod.GET)
  public ResponseBody checkIn(
      @RequestParam(name = "barCode") String barCode,
      @RequestParam(name = "embarkationLocation") String embarkationLocation,
      @RequestParam(name = "lineName") String lineName) {
    return controlService.checkIn(barCode, embarkationLocation, lineName);
  }

  @RequestMapping(value = "/checkOut", method = RequestMethod.GET)
  public ResponseBody checkOut(
      @RequestParam(name = "barCode") String barCode,
      @RequestParam(name = "embarkationLocation") String embarkationLocation,
      @RequestParam(name = "lineName") String lineName) {
    return controlService.checkOut(barCode, embarkationLocation, lineName);
  }
}
