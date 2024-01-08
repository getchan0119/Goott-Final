package com.example.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pagecontroller {

  @RequestMapping("/membersearch")
  public String membersearch() {
    return "membersearch";
  }

  @RequestMapping("/about")
  public String about() {
    return "about";
  }

  @RequestMapping("/services")
  public String services() {
    return "services";
  }

  @RequestMapping("products")
  public String products() {
    return "products";
  }

  @RequestMapping("/contact")
  public String contact() {
    return "contact";
  }

  @RequestMapping("signin")
  public String signin() {
    return "signin";
  }

  @RequestMapping("/signup")
  public String signup() {
    return "signup";
  }

}
