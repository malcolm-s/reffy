package com.mstone.reffy.home;

import java.util.Date;

import com.mstone.reffy.category.CategoryRepository;
import com.mstone.reffy.referendum.ReferendumRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class HomeController {
  private final ReferendumRepository referendums;
  private final CategoryRepository categories;

  public HomeController(ReferendumRepository referendums, CategoryRepository categories) {
    this.referendums = referendums;
    this.categories = categories;
  }

  @GetMapping("/")
  public String index(Model model, @RequestParam(required = false) Integer categoryId) {
    var date = new Date();

    log.info("date: " + date);
    log.info("categoryId: " + categoryId);
    model.addAttribute("date", date);
    model.addAttribute("referendumCount", referendums.count());
    model.addAttribute("referendums", referendums.findAll(Sort.by(Direction.DESC, "updated")));
    model.addAttribute("categories", categories.findAll());
    return "index";
  }
}