package com.mstone.reffy.referendum;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.mstone.reffy.vote.Vote;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Referendum {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String question;

  @Column
  @Lob
  private String description;

  @OneToMany(mappedBy = "referendum")
  @ToString.Exclude
  private Collection<Vote> votes;

  @Column
  private long votesForCount;

  @Column
  private long votesAgainstCount;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime updated;

  public int totalVotes() {
    return this.votes.size();
  }
}
