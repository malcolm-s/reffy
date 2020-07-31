package com.mstone.reffy.referendum;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReferendumRepository extends JpaRepository<Referendum, Integer> {
  List<Referendum> findAllByCategoriesId(Integer categoryId);

  @Transactional
  @Modifying
  @Query("UPDATE Referendum r set r.votesForCount = r.votesForCount + 1 WHERE r.id = :id")
  void incrementVotesForCountById(@Param("id") Integer id);

  @Transactional
  @Modifying
  @Query("UPDATE Referendum r set r.votesAgainstCount = r.votesAgainstCount + 1 WHERE r.id = :id")
  void incrementVotesAgainstCountById(@Param("id") Integer id);

  Page<Referendum> findAllWithCategoriesBy(Pageable page);
  
  @EntityGraph(attributePaths = { "categories", "states" })
  Optional<Referendum> findWithRelationsById(Integer id);
}