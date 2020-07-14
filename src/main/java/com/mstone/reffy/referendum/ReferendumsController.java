package com.mstone.reffy.referendum;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReferendumsController {
  private final ReferendumService referendumService;
  private final ReferendumRepository referendums;

  public ReferendumsController(ReferendumService referendumService, ReferendumRepository referendumRepository) {
    this.referendumService = referendumService;
    this.referendums = referendumRepository;
  }

  @GetMapping("/referendums/new")
  public String newReferendum(Model model) {
    model.addAttribute("vm", new NewReferendumViewModel());
    return "referendums/new";
  }

  @PostMapping("/referendums/new")
  public ModelAndView makeNewReferendum(@ModelAttribute NewReferendumViewModel vm, Model model) {
    var referendum = referendumService.saveReferendum(vm);
    model.addAttribute("id", referendum.getId());
    return new ModelAndView("redirect:/referendums/{id}", model.asMap());
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

      var vm = new CastVoteViewModel();
      model.addAttribute("vm", vm);

    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return "referendums/vote";
  }

  @PostMapping("/referendums/{id}/vote")
  public ModelAndView vote(@PathVariable Integer id, Model model, CastVoteViewModel vm) {
    var referendum = referendums.findById(id);

    if (referendum.isPresent()) {
      referendumService.voteFor(referendum.get(), vm);
      return new ModelAndView("redirect:/referendums/{id}", model.asMap());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }
}