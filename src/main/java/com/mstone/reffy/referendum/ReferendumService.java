package com.mstone.reffy.referendum;

import com.mstone.reffy.email.EmailService;
import com.mstone.reffy.vote.Vote;
import com.mstone.reffy.vote.VoteChoice;
import com.mstone.reffy.vote.VoteService;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReferendumService {
  private final EmailService emailService;
  private final ReferendumRepository referendums;
  private final VoteService voteService;

  public ReferendumService(ReferendumRepository referendums, EmailService emailService, VoteService voteService) {
    this.emailService = emailService;
    this.referendums = referendums;
    this.voteService = voteService;
  }

  public Referendum saveReferendum(NewReferendumViewModel vm) {
    var referendum = new Referendum();
    referendum.setQuestion(vm.getQuestion());
    referendum.setDescription(vm.getDescription());
    referendums.save(referendum);

    emailService.sendNewReferendumEmail(referendum);

    log.info("created referendum: {}", referendum);

    return referendum;
  }

  public void voteFor(Referendum referendum, CastVoteViewModel vm) {
    log.info("voting on referendum: {}, {}", referendum, vm);

    var vote = voteService.saveVote(referendum, vm.getChoice());
    updateVoteCount(referendum, vote);
  }

  private void updateVoteCount(Referendum referendum, Vote vote) {
    referendum.getVotes().add(vote);
    
    if (vote.getChoice().equals(VoteChoice.FOR)) {
      referendum.setVotesForCount(referendum.getVotesForCount() + 1);
    } else {
      referendum.setVotesAgainstCount(referendum.getVotesAgainstCount() + 1);
    }
    referendums.save(referendum);
  }
}