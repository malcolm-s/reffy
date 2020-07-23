package com.mstone.reffy.referendum;

import java.security.Principal;

import javax.validation.Valid;

import com.mstone.reffy.user.UserRepository;
import com.mstone.reffy.vote.VoteService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReferendumVoteController {
  private final VoteService voteService;
  private final UserRepository userRepository;
  private final ReferendumRepository referendums;

  public ReferendumVoteController(VoteService voteService, ReferendumRepository referendumRepository,
      UserRepository userRepository) {
    this.voteService = voteService;
    this.referendums = referendumRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("/referendums/{id}/vote")
  public String vote(@PathVariable Integer id, Model model, @ModelAttribute("vm") CastVoteForm vm) {
    return "referendums/vote";
  }

  @PostMapping("/referendums/{id}/vote")
  public String vote(@PathVariable Integer id, Model model, @ModelAttribute("vm") @Valid CastVoteForm vm,
      BindingResult binding, Principal principal) {
    if (binding.hasErrors()) {
      return "referendums/vote";
    }

    var user = userRepository.findByEmail(principal.getName());
    var referendum = referendums.findById(id);
    voteService.voteFor(referendum.get(), user.get(), vm);
    return "redirect:/referendums/{id}";
  }

  @ModelAttribute("referendum")
  private Referendum referendum(@PathVariable Integer id) {
    return referendums.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }
}