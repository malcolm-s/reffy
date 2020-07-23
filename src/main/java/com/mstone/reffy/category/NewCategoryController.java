package com.mstone.reffy.category;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories/new")
public class NewCategoryController {
  private final CategoryService categoryService;

  public NewCategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public String newCategory(@ModelAttribute("vm") NewCategoryForm vm) {
    return "category/new";
  }

  @PostMapping
  public String makeNewCategory(@ModelAttribute("vm") @Valid NewCategoryForm vm, BindingResult binding) {
    if (binding.hasErrors()) {
      return "category/new";
    }
    categoryService.saveCategory(vm);
    return "redirect:/categories";
  }
}