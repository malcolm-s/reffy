package com.mstone.reffy.vote;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
  Long countByCreatedAfter(LocalDateTime datetime);
}
