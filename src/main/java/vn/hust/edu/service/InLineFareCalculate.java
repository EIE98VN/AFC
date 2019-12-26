package vn.hust.edu.service;

import vn.hust.edu.constant.Fare;
import vn.hust.edu.model.Distance;
import vn.hust.edu.model.Line;
import vn.hust.edu.model.Station;
import vn.hust.edu.service.FareCalculate;

public class InLineFareCalculate implements FareCalculate {

  @Override
  public float calculate(Station embarkation, Station disembarkation, Line line) {

    Distance embarkationDistance = embarkation.findByLineId(line.getId());
    Distance disembarkationDistance = disembarkation.findByLineId(line.getId());

    if(embarkationDistance==null||disembarkationDistance==null) return 0;

    float distance = Math.abs(embarkationDistance.getDistance() - disembarkationDistance.getDistance());

    if (distance == 0) return 0;
    if (distance <= Fare.BASE_DISTANCE) return Fare.BASE_FARE;
    else return (float) (Fare.BASE_FARE + Math.ceil((distance - Fare.BASE_DISTANCE) / Fare.ADDITION_DISTANCE) * Fare.ADDITION_FARE);
  }
}
