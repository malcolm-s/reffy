package com.mstone.reffy.referendum;

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

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

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
  private @With ReferendumStatus status;

  @ManyToOne
  @JoinColumn(name = "referendum_id", nullable = false)
  @ToString.Exclude
  private Referendum referendum;
}