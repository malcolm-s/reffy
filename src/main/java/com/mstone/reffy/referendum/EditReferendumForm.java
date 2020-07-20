package com.mstone.reffy.referendum;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EditReferendumForm {
  @NotBlank
  private String question;

  @NotBlank
  private String description;

  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate votingOpens;

  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate votingCloses;
}