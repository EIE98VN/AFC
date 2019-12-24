package vn.hust.edu.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(
        name = "station",
        indexes = {@Index(name = "idx_location", columnList = "location", unique = true)})
public class Station {
    @Id
    private String id;

    @Column(name = "location")
    private String location;

    @Column(name = "distance")
    private float distance;
}

