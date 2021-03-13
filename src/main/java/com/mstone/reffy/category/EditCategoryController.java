package com.mstone.reffy.category;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/categories/{id}/edit")
public class EditCategoryController {
  private final CategoryRepository categories;
  private final CategoryService categoryService;

  public EditCategoryController(CategoryRepository categories, CategoryService categoryService) {
    this.categories = categories;
    this.categoryService = categoryService;
  }

  @GetMapping
  public String editCategory(@PathVariable Integer id, @ModelAttribute("vm") EditCategoryForm vm) {
    var category = categories.findById(id);

    if (category.isPresent()) {
      vm.setName(category.get().getName());
      return "category/edit";
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public String doEditCategory(
    @PathVariable Integer id,
    @ModelAttribute("vm") @Valid EditCategoryForm vm,
    BindingResult binding
  ) {
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

  @ModelAttribute("categoryId")
  private Integer categoryId(@PathVariable Integer id) {
    return id;
  }
}
