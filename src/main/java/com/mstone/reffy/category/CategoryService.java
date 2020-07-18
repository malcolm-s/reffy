package com.mstone.reffy.category;

import org.springframework.stereotype.Component;

@Component
public class CategoryService {
  private final CategoryRepository categories;

  public CategoryService(CategoryRepository categories) {
    this.categories = categories;
  }

  public Category saveCategory(NewCategoryForm vm) {
    var category = new Category();
    category.setName(vm.getName());
    return categories.save(category);
  }

  public Category editCategory(Category category, EditCategoryForm vm) {
    category.setName(vm.getName());
    return categories.save(category);
  }
}