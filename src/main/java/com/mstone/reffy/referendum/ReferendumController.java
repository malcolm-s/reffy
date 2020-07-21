package com.mstone.reffy.referendum;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReferendumController {
  private final ReferendumService referendumService;
  private final ReferendumRepository referendums;

  public ReferendumController(ReferendumService referendumService, ReferendumRepository referendumRepository) {
    this.referendumService = referendumService;
    this.referendums = referendumRepository;
  }

  @GetMapping("/referendums")
  public String index(Model model, @RequestParam(required = false) Integer categoryId) {
    var referendumsToShow = categoryId == null ? referendums.findAll() : referendums.findAllByCategoriesId(categoryId);
    model.addAttribute("referendums", referendumsToShow);
    return "referendums/index";
  }

  @GetMapping("/referendums/{id}")
  public String view(@PathVariable Integer id, Model model) {
    var referendum = referendums.findById(id);

    if (referendum.isPresent()) {
      model.addAttribute("referendum", referendum.get());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return "referendums/view";
  }

  @GetMapping("/referendums/{id}/vote")
  public String vote(@PathVariable Integer id, Model model) {
    var referendum = referendums.findById(id);

    if (referendum.isPresent()) {
      model.addAttribute("referendum", referendum.get());

      var vm = new CastVoteForm();
      model.addAttribute("vm", vm);

    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return "referendums/vote";
  }

  @PostMapping("/referendums/{id}/vote")
  public ModelAndView vote(@PathVariable Integer id, Model model, @ModelAttribute("vm") @Valid CastVoteForm vm,
      BindingResult binding) {
    var referendum = referendums.findById(id);

    if (referendum.isPresent()) {
      if (binding.hasErrors()) {
        model.addAttribute("referendum", referendum.get());
        return new ModelAndView("referendums/vote", model.asMap());
      }
      referendumService.voteFor(referendum.get(), vm);
      return new ModelAndView("redirect:/referendums/{id}", model.asMap());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }
}