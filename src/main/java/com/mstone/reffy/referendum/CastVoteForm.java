package com.mstone.reffy.referendum;

import javax.validation.constraints.NotNull;

import com.mstone.reffy.vote.VoteChoice;

import lombok.Data;

@Data
public class CastVoteForm {
  @NotNull
  private VoteChoice choice;
}