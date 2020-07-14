package com.mstone.reffy.vote;

import com.mstone.reffy.referendum.Referendum;

import org.springframework.stereotype.Component;

@Component
public class VoteService {
  private final VoteRepository votes;

  public VoteService(VoteRepository votes) {
    this.votes = votes;
  }

  public Vote saveVote(Referendum referendum, VoteChoice choice) {
    var vote = new Vote();
    vote.setChoice(choice);
    vote.setReferendum(referendum);
    return votes.save(vote);
  }
}