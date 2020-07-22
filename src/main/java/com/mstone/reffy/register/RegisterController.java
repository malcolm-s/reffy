package com.mstone.reffy.register;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
  @GetMapping("/register")
  public String register(@ModelAttribute("vm") RegisterForm vm) {
    return "register/index";
  }

  @PostMapping("/register")
  public String register(@ModelAttribute("vm") @Valid RegisterForm vm, BindingResult binding) {
    if (binding.hasErrors()) {
      return "register/index";
    }

    return "redirect:/register/complete";
  }

  @GetMapping("/register/complete")
  public String registerComplete() {
    return "register/complete";
  }
}