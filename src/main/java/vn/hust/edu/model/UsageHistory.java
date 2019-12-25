package vn.hust.edu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(
        name = "using_history",
        indexes = {@Index(name = "idx_history", columnList = "certificate_id")})
public class UsageHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "certificate_id", foreignKey = @ForeignKey(name = "FK_HISTORY_CERTIFICATE"))
  private Certificate certificate;

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
