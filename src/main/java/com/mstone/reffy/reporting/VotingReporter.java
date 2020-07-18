package com.mstone.reffy.reporting;

import java.time.Duration;
import java.time.LocalDateTime;

import com.mstone.reffy.vote.VoteRepository;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
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