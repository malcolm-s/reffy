package com.mstone.reffy.referendum;

import com.mstone.reffy.vote.VoteChoice;

import lombok.Data;

@Data
public class CastVoteViewModel {
  private VoteChoice choice;
}