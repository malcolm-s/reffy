package com.mstone.reffy.vote;

import com.mstone.reffy.referendum.Referendum;
import com.mstone.reffy.user.User;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
  Long countByCreatedAfter(LocalDateTime datetime);

  boolean existsByUserAndReferendum(User user, Referendum referendum);
}
