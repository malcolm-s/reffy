package com.mstone.reffy.referendum.state;

import com.mstone.reffy.referendum.Referendum;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReferendumStateService {
  private final ReferendumStateRepository referendumStateRepository;

  public void updateStatus(Referendum referendum, ReferendumStatus status) {
    var state = new ReferendumState();
    state.setReferendum(referendum);
    state.setStatus(status);

    referendumStateRepository.save(state);
  }
}