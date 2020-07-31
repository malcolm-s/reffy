package com.mstone.reffy.referendum;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReferendumService {
  private final ApplicationEventPublisher eventPublisher;
  private final ReferendumRepository referendums;
  private final ReferendumStateRepository referendumStateRepository;

  public ReferendumService(ReferendumRepository referendums, ApplicationEventPublisher eventPublisher,
      ReferendumStateRepository referendumStateRepository) {
    this.referendums = referendums;
    this.eventPublisher = eventPublisher;
    this.referendumStateRepository = referendumStateRepository;
  }

  @Transactional
  public Referendum saveReferendum(NewReferendumForm vm) {
    var referendum = referendumFromVm(vm);
    referendums.save(referendum);
    referendumStateRepository.save(initialState(referendum));
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

  private Referendum referendumFromVm(NewReferendumForm vm) {
    var referendum = new Referendum();
    referendum.setQuestion(vm.getQuestion());
    referendum.setDescription(vm.getDescription());
    referendum.setVotingOpens(vm.getVotingOpens());
    referendum.setVotingCloses(vm.getVotingCloses());
    referendum.setCategories(vm.getCategories());
    return referendum;
  }

  private ReferendumState initialState(Referendum referendum) {
    var state = new ReferendumState();
    state.setReferendum(referendum);
    state.setStatus(ReferendumStatus.CREATED);
    return state;
  }
}