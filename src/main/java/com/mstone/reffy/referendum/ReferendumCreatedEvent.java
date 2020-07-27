package com.mstone.reffy.referendum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReferendumCreatedEvent {
  private Integer referendumId;
}