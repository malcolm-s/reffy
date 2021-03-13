package com.mstone.reffy.referendum;

import com.mstone.reffy.vote.VoteChoice;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CastVoteForm {
  @NotNull
  private VoteChoice choice;
}
