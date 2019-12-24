package vn.hust.edu;

import vn.hust.edu.constant.Fare;
import vn.hust.edu.model.Station;

public class InLineFareCalculate implements FareCalculate {

  @Override
  public float calculate(Station embarkation, Station disembarkation) {

    float distance = Math.abs(embarkation.getDistance() - disembarkation.getDistance());

    if (distance == 0) return 0;
    if (distance <= Fare.BASE_DISTANCE) return Fare.BASE_FARE;
    else return (float) (Fare.BASE_FARE + Math.ceil((distance - Fare.BASE_DISTANCE) / Fare.ADDITION_DISTANCE) * Fare.ADDITION_FARE);
  }
}
