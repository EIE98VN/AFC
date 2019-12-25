package vn.hust.edu.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "line")
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "line")
    Collection<Distance> distances;
}
