package com.mstone.reffy.migrations.upgrades;

import com.mstone.reffy.migrations.Migration;
import com.mstone.reffy.referendum.ReferendumRepository;
import com.mstone.reffy.referendum.state.ReferendumStateService;
import com.mstone.reffy.referendum.state.ReferendumStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AddReferendumStateMigration implements Migration {
  private final ReferendumRepository referendumRepository;
  private final ReferendumStateService referendumStateService;

  @Override
  public String getName() {
    return getClass().getSimpleName();
  }

  @Override
  public void run() {
    var referendums = referendumRepository.findAll();
    for (var referendum : referendums) {
      if (referendum.getStates().size() < 1) {
        log.info("adding initial state to referendum: {}", referendum);
        referendumStateService.updateStatus(referendum, ReferendumStatus.CREATED);
      }
    }
  }
}
