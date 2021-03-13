package com.mstone.reffy.category;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewCategoryForm {
  @NotBlank
  private String name;
}
