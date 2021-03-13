package com.mstone.reffy.referendum;

import com.mstone.reffy.referendum.state.ReferendumStateService;
import com.mstone.reffy.referendum.state.ReferendumStatus;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReferendumService {
  private final ApplicationEventPublisher eventPublisher;
  private final ReferendumRepository referendums;
  private final ReferendumStateService referendumStateService;

  @Transactional
  public Referendum saveReferendum(NewReferendumForm vm) {
    var referendum = referendumFromVm(vm);
    referendums.save(referendum);
    referendumStateService.updateStatus(referendum, ReferendumStatus.CREATED);
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
}
