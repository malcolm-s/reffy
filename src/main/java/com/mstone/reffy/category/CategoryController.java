package com.mstone.reffy.category;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class CategoryController {
  private final CategoryRepository categories;
  private final CategoryService categoryService;

  public CategoryController(CategoryRepository categories, CategoryService categoryService) {
    this.categories = categories;
    this.categoryService = categoryService;
  }

  @GetMapping("/categories")
  public String index(Model model) {
    model.addAttribute("categories", categories.findAll());
    return "category/index";
  }

  @GetMapping("/categories/new")
  public String newCategory(@ModelAttribute("vm") NewCategoryForm vm) {
    return "category/new";
  }

  @PostMapping("/categories/new")
  public String makeNewCategory(@ModelAttribute("vm") @Valid NewCategoryForm vm, BindingResult binding) {
    if (binding.hasErrors()) {
      return "category/new";
    }
    categoryService.saveCategory(vm);
    return "redirect:/categories";
  }

  @GetMapping("/categories/{id}/edit")
  public String editCategory(@PathVariable Integer id, @ModelAttribute("vm") EditCategoryForm vm) {
    var category = categories.findById(id);

    if (category.isPresent()) {
      vm.setName(category.get().getName());
      return "category/edit";
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/categories/{id}/edit")
  public String doEditCategory(@PathVariable Integer id, @ModelAttribute("vm") @Valid EditCategoryForm vm,
      BindingResult binding) {
    var category = categories.findById(id);

    if (category.isPresent()) {
      if (binding.hasErrors()) {
        return "category/edit";
      }
      categoryService.editCategory(category.get(), vm);
      return "redirect:/categories";
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }
}