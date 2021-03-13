package com.mstone.reffy.referendum;

import com.mstone.reffy.category.Category;
import com.mstone.reffy.validation.DateInFuture;
import java.time.LocalDate;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class NewReferendumForm {
  @NotBlank
  private String question;

  @NotBlank
  private String description;

  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate votingOpens;

  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @DateInFuture
  private LocalDate votingCloses;

  private Set<Category> categories;
}
