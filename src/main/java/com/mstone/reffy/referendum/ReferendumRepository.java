package com.mstone.reffy.referendum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferendumRepository extends JpaRepository<Referendum, Integer> {
  public List<Referendum> findAllByCategoriesId(Integer categoryId);
}