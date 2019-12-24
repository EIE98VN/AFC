package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.model.Station;
import vn.hust.edu.repository.StationRepository;

import java.util.List;

@Service
public class StationService {

  @Autowired StationRepository stationRepository;

  /**
   * @param stationId station id in string form
   * @return instance of station found
   */
  public Station findByStaionId(String stationId) {
    return stationRepository.findById(stationId);
  }

  /**
   * @param location location of station in string form
   * @return instance of station found
   */
  public Station findByLocation(String location) {
    return stationRepository.findByLocation(location);
  }

  /**
   * @param station an instance of station
   * @return saved station instance
   */
  public Station save(Station station) {
    return stationRepository.save(station);
  }

  /** @return list of all station instances */
  public List<Station> findAll() {
    return stationRepository.findAll();
  }
}
