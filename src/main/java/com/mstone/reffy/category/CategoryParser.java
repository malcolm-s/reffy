package com.mstone.reffy.category;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Parser;
import org.springframework.stereotype.Component;

@Component
public class CategoryParser implements Parser<Category> {
  private final CategoryRepository categories;

  public CategoryParser(CategoryRepository categories) {
    this.categories = categories;
  }

  @Override
  public Category parse(String text, Locale locale) throws ParseException {
    return categories.findById(Integer.valueOf(text)).orElseThrow();
  }
}
