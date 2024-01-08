package com.example.final_project.products.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductsController {
    @GetMapping("/products")
    @ResponseBody
    public String products() {
       return "products";
    }
}
