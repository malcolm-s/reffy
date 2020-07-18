package com.mstone.reffy.vote;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mstone.reffy.referendum.Referendum;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Vote {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  @JoinColumn(name = "referendum_id", nullable = false)
  @ToString.Exclude
  private Referendum referendum;

  @Enumerated(EnumType.STRING)
  private VoteChoice choice;

  @CreationTimestamp
  private LocalDateTime created;
}