package vn.hust.edu;

import vn.hust.edu.constant.Type;
import vn.hust.edu.model.Certificate;
import vn.hust.edu.model.support.ResponseBody;

public class GeneralUtil {

  /**
   *
   * @param barCode barCode of ticket/card in string form
   * @return status whether this barCode belongs to a card
   */
  public static boolean isCard(String barCode) {

    if (barCode.equals(barCode.toUpperCase())) return true;

    return false;
  }

  /**
   *
   * @param status status Sucess/Fail (0/1)
   * @param type instance of PaymentType
   * @param message response message
   * @return response to card in ResponseBody form
   */
  public static ResponseBody createResponse(int status, Certificate certificate, String type, String message) {

    ResponseBody responseBody = new ResponseBody();
    responseBody.setStatus(status);
    responseBody.setType(type);
    responseBody.setMessage(message);
    responseBody.setData(certificate);

    return responseBody;
  }

  /**
   *
   * @param status status Success/Fail (0/1)
   * @param message response message
   * @param type type of ticket/card
   * @return response in the form of ResponseBody
   */
  public static ResponseBody createResponse(int status, String message, String type) {

    ResponseBody responseBody = new ResponseBody();
    responseBody.setStatus(status);
    responseBody.setMessage(message);
    responseBody.setType(type);
    return responseBody;
  }

//  public static PaymentType getCode(String barCode){
//    String code = "";
//    if(isCard(barCode)){
//      CardScanner cardScanner = CardScanner.getInstance();
//      code = cardScanner.process(barCode);
//    }esle{
//      TicketRecognizer ticketRecognizer = TicketRecognizer.getInstance();
//      code = ticketRecognizer.process(barCode);
//    }
//
//    return code;
//  }
}
