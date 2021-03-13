package com.mstone.reffy.referendum.state;

import com.mstone.reffy.referendum.Referendum;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReferendumState {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @CreationTimestamp
  @Column(nullable = false)
  private LocalDateTime created;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @With
  private ReferendumStatus status;

  @ManyToOne
  @JoinColumn(name = "referendum_id", nullable = false)
  @ToString.Exclude
  private Referendum referendum;
}
