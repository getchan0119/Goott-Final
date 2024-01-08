package com.example.final_project.controller;

import com.example.final_project.products.entity.Products;
import com.example.final_project.products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class pagecontroller {

  private final ProductsService productsService; // 제품 서비스

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
  public String products(Model model) {
    List<Products> productsList = this.productsService.getlist();
    model.addAttribute("productsList", productsList);
    return "products/products";
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
