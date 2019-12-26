package vn.hust.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.hust.edu.constant.Type;
import vn.hust.edu.model.Line;
import vn.hust.edu.model.Station;
import vn.hust.edu.model.support.ResponseBody;
import vn.hust.edu.repository.OWTicketRepository;
import vn.hust.edu.repository.PrepaidCardRepository;
import vn.hust.edu.service.ControlService;
import vn.hust.edu.service.LineService;
import vn.hust.edu.service.StationService;

import java.util.List;

@Controller
public class RoutingController {

  @Autowired PrepaidCardRepository repository;

  @Autowired OWTicketRepository owTicketRepository;

  @Autowired ControlService controlService;

  @Autowired StationService stationService;

  @Autowired LineService lineService;

  /**
   *
   * @param model object model is passed into this method for adding attributes, for the purpose of returning data to view
   * @return check-in Form view
   */
  @RequestMapping(value = "/checkIn", method = RequestMethod.GET)
  public String checkIn(Model model) {
    List<Station> stationList = stationService.findAll();
    List<Line> lineList = lineService.findAll();
    model.addAttribute("stationList", stationList);
    model.addAttribute("lineList", lineList);
    return "checkIn/form";
  }

  /**
   *
   * @param barCode barCode from the ticket/card in the form of a string
   * @param embarkationLocation location of the embarkation station in the form of a string
   * @param model
   * @param lineName name of line
   * @return corresponding view is returned according to request status and type of ticket/card
   */
  @RequestMapping(value = "/checkIn", method = RequestMethod.POST)
  public String checkIn(
      @RequestParam(name = "barCode") String barCode,
      @RequestParam(name = "embarkationLocation") String embarkationLocation,
      @RequestParam(name = "lineName") String lineName,
      Model model) {

    ResponseBody responseBody = controlService.checkIn(barCode, embarkationLocation, lineName);

    model.addAttribute("response", responseBody);

    if (responseBody.getStatus() == 1) {
      String embarkation = responseBody.getHistory().getEmbarkation().getLocation();
      // get type of response data, and return corresponding view
      String type = responseBody.getType();
      model.addAttribute("embarkation", embarkation);
      switch (type) {
        case Type.TICKET_ONEWAY:
          return "checkIn/ticket1way";
        case Type.TICKET_24H:
          return "checkIn/ticket24h";
        case Type.CARD:
          return "checkIn/Card";
        default:
          return "checkIn/error";
      }
    } else {
      model.addAttribute("response", responseBody);
      return "checkIn/error";
    }
  }

  /**
   *
   * @param model object model is passed into this method for returning data to view
   * @return check-out form view
   */
  @RequestMapping(value = "/checkOut", method = RequestMethod.GET)
  public String checkOut(Model model) {
    List<Station> stationList = stationService.findAll();
    List<Line> lineList = lineService.findAll();
    model.addAttribute("stationList", stationList);
    model.addAttribute("lineList", lineList);
    return "checkOut/form";
  }

  /**
   *
   * @param barCode barCode from the ticket/card in the form of a string
   * @param disembarkationLocation location of the disembarkation station in the form of a string
   * @param model object model for passing data to the view
   * @param lineName name of line
   * @return corresponding view is returned according to the status request and type of ticket/card
   */
  @RequestMapping(value = "/checkOut", method = RequestMethod.POST)
  public String checkOut(
      @RequestParam(name = "barCode") String barCode,
      @RequestParam(name = "disembarkationLocation") String disembarkationLocation,
      @RequestParam(name = "lineName") String lineName,
      Model model) {
    ResponseBody responseBody = controlService.checkOut(barCode, disembarkationLocation, lineName);

    model.addAttribute("response", responseBody);

    if (responseBody.getStatus() == 1) {
      String type = responseBody.getType();
      String embarkation = responseBody.getHistory().getEmbarkation().getLocation();
      String disembarkation = responseBody.getHistory().getDisembarkation().getLocation();
      model.addAttribute("embarkation", embarkation);
      model.addAttribute("disembarkation", disembarkation);
      switch (type) {
        case Type.TICKET_ONEWAY:
          return "checkOut/ticket1way";
        case Type.TICKET_24H:
          return "checkOut/ticket24h";
        case Type.CARD:
          return "checkOut/Card";
        default:
          return "checkOut/error";
      }
    } else {
      model.addAttribute("response", responseBody);
      return "checkOut/error";
    }
  }
}
