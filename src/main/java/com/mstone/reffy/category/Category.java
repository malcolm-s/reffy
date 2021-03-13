package com.mstone.reffy.category;

import com.mstone.reffy.referendum.Referendum;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String name;

  @ManyToMany(mappedBy = "categories")
  @ToString.Exclude
  private Collection<Referendum> referendums;
}
