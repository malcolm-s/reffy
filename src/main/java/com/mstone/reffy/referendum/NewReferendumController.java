package com.mstone.reffy.referendum;

import com.mstone.reffy.category.Category;
import com.mstone.reffy.category.CategoryRepository;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/referendums/new")
public class NewReferendumController {
  private final ReferendumService referendumService;
  private final CategoryRepository categories;

  public NewReferendumController(
    ReferendumService referendumService,
    CategoryRepository categories
  ) {
    this.referendumService = referendumService;
    this.categories = categories;
  }

  @GetMapping
  public String newReferendum(@ModelAttribute("vm") NewReferendumForm vm) {
    return "referendums/new";
  }

  @PostMapping
  public ModelAndView makeNewReferendum(
    @ModelAttribute("vm") @Valid NewReferendumForm vm,
    BindingResult binding,
    RedirectAttributes redirect
  ) {
    if (binding.hasErrors()) {
      return new ModelAndView("referendums/new", redirect.asMap());
    }
    var referendum = referendumService.saveReferendum(vm);
    redirect.addAttribute("id", referendum.getId());
    return new ModelAndView("redirect:/referendums/{id}", redirect.asMap());
  }

  @ModelAttribute("categories")
  private Collection<Category> categories() {
    return categories.findAll();
  }
}
