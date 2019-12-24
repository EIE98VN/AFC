package vn.hust.edu.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(
        name = "using_history",
        indexes = {@Index(name = "idx_history", columnList = "certificate_id")})
public class UsingHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "certificate_id")
  private String certificateId;

  @Column(name = "check_in_at")
  private Date checkInAt;

  @Column(name = "check_out_at")
  private Date checkOutAt;

  @ManyToOne
  @JoinColumn(name = "embarkation_id", foreignKey = @ForeignKey(name = "FK_HISTORY_EMBARKATION"))
  private Station embarkation;

  @ManyToOne
  @JoinColumn(name = "disembarkation_id", foreignKey = @ForeignKey(name = "FK_HISTORY_DISEMBARKATION"))
  private Station disembarkation;
}
