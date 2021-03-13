package com.mstone.reffy.user;

import com.mstone.reffy.vote.Vote;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Setter
@Getter
@ToString
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(unique = true)
  private String email;

  @Column
  private String password;

  @Column
  private String govId;

  @CreationTimestamp
  @Column
  private LocalDateTime created;

  @UpdateTimestamp
  @Column
  private LocalDateTime updated;

  @OneToMany(mappedBy = "user")
  @ToString.Exclude
  private Collection<Vote> votes;
}
