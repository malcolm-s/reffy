package com.mstone.reffy.category;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
  private final CategoryRepository categories;

  public CategoryController(CategoryRepository categories) {
    this.categories = categories;
  }

  @GetMapping("/categories")
  public String index(Model model) {
    model.addAttribute("categories", categories.findAll());
    return "category/index";
  }
}
