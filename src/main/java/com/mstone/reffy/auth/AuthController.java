package com.mstone.reffy.auth;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
  @GetMapping("/login")
  public String login(@ModelAttribute("vm") LoginForm vm) {
    return "auth/login";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute("vm") @Valid LoginForm vm, BindingResult binding) {
    if (binding.hasErrors()) {
      return "auth/login";
    }
    // do login
    return "redirect:/";
  }
}