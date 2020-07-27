package com.mstone.reffy.referendum;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReferendumService {
  private final ApplicationEventPublisher eventPublisher;
  private final ReferendumRepository referendums;

  public ReferendumService(ReferendumRepository referendums, ApplicationEventPublisher eventPublisher) {
    this.referendums = referendums;
    this.eventPublisher = eventPublisher;
  }

  public Referendum saveReferendum(NewReferendumForm vm) {
    var referendum = new Referendum();
    referendum.setQuestion(vm.getQuestion());
    referendum.setDescription(vm.getDescription());
    referendum.setVotingOpens(vm.getVotingOpens());
    referendum.setVotingCloses(vm.getVotingCloses());
    referendum.setCategories(vm.getCategories());
    referendums.save(referendum);
    log.info("created referendum: {}", referendum);

    eventPublisher.publishEvent(new ReferendumCreatedEvent(referendum.getId()));
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