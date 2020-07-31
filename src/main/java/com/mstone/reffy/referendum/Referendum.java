package com.mstone.reffy.referendum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.mstone.reffy.category.Category;
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
  @Column
  private LocalDateTime created;

  @UpdateTimestamp
  @Column
  private LocalDateTime updated;

  @Column
  private LocalDate votingOpens;

  @Column
  private LocalDate votingCloses;

  @ManyToMany
  @JoinTable(joinColumns = {
      @JoinColumn(name = "referendum_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, updatable = false))
  private Collection<Category> categories;

  @OneToMany(mappedBy = "referendum", fetch = FetchType.EAGER)
  private Collection<ReferendumState> states;

  public int totalVotes() {
    return this.votes.size();
  }
}
