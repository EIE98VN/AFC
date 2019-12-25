package vn.hust.edu.model.support;

import lombok.Data;
import vn.hust.edu.model.Certificate;
import vn.hust.edu.model.UsageHistory;

@Data
public class ResponseBody {

  int status;

  String type;

  String message;

  Certificate data;

  UsageHistory history;
}
