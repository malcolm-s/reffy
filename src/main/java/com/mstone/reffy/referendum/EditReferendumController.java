package com.mstone.reffy.referendum;

import java.util.Collection;

import javax.validation.Valid;

import com.mstone.reffy.category.Category;
import com.mstone.reffy.category.CategoryRepository;

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
@RequestMapping("/referendums/{id}/edit")
public class EditReferendumController {
  private final ReferendumService referendumService;
  private final ReferendumRepository referendums;
  private final CategoryRepository categories;

  public EditReferendumController(ReferendumService referendumService, ReferendumRepository referendumRepository,
      CategoryRepository categories) {
    this.referendumService = referendumService;
    this.referendums = referendumRepository;
    this.categories = categories;
  }

  @GetMapping
  public String edit(@PathVariable Integer id, @ModelAttribute("vm") EditReferendumForm vm) {
    return referendums.findById(id).map(r -> {
      vm.setQuestion(r.getQuestion());
      vm.setDescription(r.getDescription());
      vm.setVotingOpens(r.getVotingOpens());
      vm.setVotingCloses(r.getVotingCloses());
      vm.setCategories(r.getCategories());
      return "referendums/edit";
    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public String doEdit(@PathVariable Integer id, @ModelAttribute("vm") @Valid EditReferendumForm vm,
      BindingResult binding) {
    if (binding.hasErrors()) {
      return "referendums/edit";
    }
    return referendums.findById(id).map(r -> {
      referendumService.editReferendum(r, vm);
      return "redirect:/referendums/{id}";
    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @ModelAttribute("categories")
  private Collection<Category> categories() {
    return categories.findAll();
  }
}