package vn.hust.edu;

import vn.hust.edu.model.Station;

public interface FareCalculate {

    public float calculate(Station embarkation, Station disembarkation);
}
