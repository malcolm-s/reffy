package com.mstone.reffy.referendum;

import com.mstone.reffy.email.EmailService;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReferendumService {
  private final EmailService emailService;
  private final ReferendumRepository referendums;

  public ReferendumService(ReferendumRepository referendums, EmailService emailService) {
    this.emailService = emailService;
    this.referendums = referendums;
  }

  public Referendum saveReferendum(NewReferendumForm vm) {
    var referendum = new Referendum();
    referendum.setQuestion(vm.getQuestion());
    referendum.setDescription(vm.getDescription());
    referendum.setVotingOpens(vm.getVotingOpens());
    referendum.setVotingCloses(vm.getVotingCloses());
    referendum.setCategories(vm.getCategories());
    referendums.save(referendum);

    emailService.sendNewReferendumEmail(referendum);

    log.info("created referendum: {}", referendum);

    return referendum;
  }

  public Referendum editReferendum(Referendum referendum, EditReferendumForm vm) {
    referendum.setQuestion(vm.getQuestion());
    referendum.setDescription(vm.getDescription());
    referendum.setVotingOpens(vm.getVotingOpens());
    referendum.setVotingCloses(vm.getVotingCloses());
    referendum.setCategories(vm.getCategories());
    return referendums.save(referendum);
  }
}