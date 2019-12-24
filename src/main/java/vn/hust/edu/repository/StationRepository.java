package vn.hust.edu.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.hust.edu.model.Station;

import java.util.List;

public interface StationRepository
    extends PagingAndSortingRepository<Station, Integer>, JpaSpecificationExecutor<Station> {

  /**
   *
   * @param stationId stationId in string from
   * @return instance of station found
   */
  Station findById(String stationId);

  /**
   *
   * @param location location of the station in string form
   * @return instance of station found
   */
  Station findByLocation(String location);

  /**
   *
   * @param station an instance of station
   * @return saved station instance
   */
  Station save(Station station);

  /**
   *
   * @return list of all stations found
   */
  List<Station> findAll();
}
