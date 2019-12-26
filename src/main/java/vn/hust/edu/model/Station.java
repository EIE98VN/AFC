package vn.hust.edu.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(
    name = "station",
    indexes = {@Index(name = "idx_location", columnList = "location", unique = true)})
public class Station {
  @Id private String id;

  @Column(name = "location")
  private String location;

  @JsonManagedReference
  @OneToMany(mappedBy = "station")
  Collection<Distance> distances;

  /**
   *
   * @param lineId id of the line
   * @return Distance object that has the lineId, null if not found
   */
  public Distance findByLineId(int lineId) {
    for (Distance distance : distances) {
      if (distance.getLine().getId() == lineId) return distance;
    }
    return null;
  }
}
