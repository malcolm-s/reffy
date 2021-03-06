package com.mstone.reffy.reporting;

import com.mstone.reffy.vote.VoteRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(
  value = "reffy.reporting.enabled",
  matchIfMissing = false,
  havingValue = "true"
)
public class VotingReporter {
  private final VoteRepository votes;

  public VotingReporter(VoteRepository votes) {
    this.votes = votes;
  }

  @Scheduled(fixedRate = 60000)
  public void reportVotes() {
    log.info("total votes: {}", votes.count());
  }

  @Scheduled(fixedRate = 5000)
  public void reportLatestVotes() {
    var fiveSecondsAgo = LocalDateTime.now().minus(Duration.ofSeconds(5));
    log.info("votes in last 5 seconds: {}", votes.countByCreatedAfter(fiveSecondsAgo));
  }
}
