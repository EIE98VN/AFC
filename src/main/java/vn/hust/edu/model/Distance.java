package vn.hust.edu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "distance")
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "station_id", foreignKey = @ForeignKey(name = "FK_DISTANCE_STATION"))
    private Station station;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "line_id", foreignKey = @ForeignKey(name = "FK_DISTANCE_LINE"))
    private Line line;

    @Column(name = "distance")
    private float distance;
}
