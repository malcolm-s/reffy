package com.mstone.reffy.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {
  @GetMapping("/register")
  public String register(@ModelAttribute("vm") RegisterForm vm) {
    return "user/register";
  }
}