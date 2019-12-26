package vn.hust.edu.service;

import vn.hust.edu.model.Line;
import vn.hust.edu.model.Station;

public interface FareCalculate {

    /**
     *
     * @param embarkation embarkation station
     * @param disembarkation disembarkation station
     * @param line
     * @return price between two station
     */
    public float calculate(Station embarkation, Station disembarkation, Line line);
}
