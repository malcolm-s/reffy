package com.mstone.reffy.referendum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;

import com.mstone.reffy.user.UserRepository;
import com.mstone.reffy.vote.VoteRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ReferendumController {
  private final ReferendumRepository referendums;
  private final VoteRepository votes;
  private final UserRepository users;

  @GetMapping("/referendums")
  public String index(Model model, @RequestParam(required = false) Integer categoryId) {
    var referendumsToShow = categoryId == null
      ? referendums.findAll()
      : referendums.findAllByCategoriesId(categoryId);
    model.addAttribute("referendums", referendumsToShow);
    return "referendums/index";
  }
  
  @GetMapping("/referendums/{id}")
  public String view(@PathVariable Integer id, Model model, Principal principal) {
    var referendum = referendums.findWithRelationsById(id);
    
    if (referendum.isPresent()) {
      model.addAttribute("referendum", referendum.get());
      var user = users.findByEmail(principal.getName());
      model.addAttribute("hasVoted", votes.existsByUserAndReferendum(user.get(), referendum.get()));
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return "referendums/view";
  }

  @GetMapping("/referendums/{id}/results")
  public String results(@PathVariable Integer id, Model model) {
    var referendum = referendums.findById(id);

    if (referendum.isPresent()) {
      model.addAttribute("referendum", referendum.get());
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return "referendums/results";
  }
}
