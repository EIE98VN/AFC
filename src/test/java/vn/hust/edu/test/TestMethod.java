package vn.hust.edu.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.hust.edu.Application;
import vn.hust.edu.constant.Message;
import vn.hust.edu.model.support.ResponseBody;
import vn.hust.edu.service.ControlService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class)
public class TestMethod {

    @Autowired
    ControlService controlService;

    @Test
    void checkOutTest1(){
        String barCode = "mncvasqw";
        String disembarkation = "Olympiades";
        String lineName = "A";
        ResponseBody responseBody = new ResponseBody();
        responseBody = controlService.checkOut(barCode, disembarkation, lineName);

        assertEquals(Message.SUCCESSFUL_CHECKOUT, responseBody.getMessage());

    }

    @Test
    void checkOutTest2(){
        String barCode = "BCDFGRWU";
        String disembarkation = "Cour Saint-Emilion";
        String lineName = "A";
        ResponseBody responseBody = new ResponseBody();
        responseBody = controlService.checkOut(barCode, disembarkation, lineName);

        assertEquals(Message.INSUFFICIENT_CARD, responseBody.getMessage());

    }

    @Test
    void checkOutTest3(){
        String barCode = "BCDEFGHN";
        String disembarkation = "Cour Saint-Emilion";
        String lineName = "B";
        ResponseBody responseBody = new ResponseBody();
        responseBody = controlService.checkOut(barCode, disembarkation, lineName);

        assertEquals(Message.NOT_CHECKIN, responseBody.getMessage());

    }

    @Test
    void testCheckIn1(){
        String barCode = "BCDEFGHN";
        String disembarkation = "Cour Saint-Emilion";
        String lineName = "A";
        ResponseBody responseBody = new ResponseBody();
        responseBody = controlService.checkIn(barCode, disembarkation, lineName);

        assertEquals(Message.SUCCESSFUL_CHECKIN, responseBody.getMessage());
    }

    @Test
    void testCheckIn2(){
        String barCode = "mnvcbnxz";
        String disembarkation = "Bibliotheque Francois Mitterrand";
        String lineName = "A";
        ResponseBody responseBody = new ResponseBody();
        responseBody = controlService.checkIn(barCode, disembarkation, lineName);

        assertEquals(Message.INVALID_EMBARKATION, responseBody.getMessage());
    }

    @Test
    void testCheckIn3(){
        String barCode = "aaaaaaaa";
        String disembarkation = "Bibliotheque Francois Mitterrand";
        String lineName = "B";
        ResponseBody responseBody = new ResponseBody();
        responseBody = controlService.checkIn(barCode, disembarkation, lineName);

        assertEquals(Message.INVALID_TICKET, responseBody.getMessage());
    }
}
