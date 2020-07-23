package com.mstone.reffy.vote;

import com.mstone.reffy.referendum.CastVoteForm;
import com.mstone.reffy.referendum.Referendum;
import com.mstone.reffy.referendum.ReferendumRepository;
import com.mstone.reffy.user.User;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VoteService {
  private final ReferendumRepository referendums;
  private final VoteRepository votes;

  public VoteService(VoteRepository votes, ReferendumRepository referendums) {
    this.votes = votes;
    this.referendums = referendums;
  }

  @Transactional
  public Vote voteFor(Referendum referendum, User user, CastVoteForm vm) {
    log.info("voting on referendum: {}, {}", referendum, vm);

    var vote = saveVote(referendum, user, vm.getChoice());
    updateVoteCount(referendum, vm.getChoice());
    return vote;
  }

  private Vote saveVote(Referendum referendum, User user, VoteChoice choice) {
    var vote = new Vote();
    vote.setChoice(choice);
    vote.setReferendum(referendum);
    vote.setUser(user);
    return votes.save(vote);
  }

  private void updateVoteCount(Referendum referendum, VoteChoice choice) {
    if (choice.equals(VoteChoice.FOR)) {
      referendums.incrementVotesForCountById(referendum.getId());
    } else {
      referendums.incrementVotesAgainstCountById(referendum.getId());
    }
  }
}