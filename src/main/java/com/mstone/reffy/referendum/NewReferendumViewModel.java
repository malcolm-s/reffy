package com.mstone.reffy.referendum;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NewReferendumViewModel {
  private String question;
  private String description;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate votingOpens;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate votingCloses;
}