package com.mstone.reffy.vote;

import java.time.LocalDateTime;

import com.mstone.reffy.referendum.Referendum;
import com.mstone.reffy.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
  Long countByCreatedAfter(LocalDateTime datetime);

  boolean existsByUserAndReferendum(User user, Referendum referendum);
}
