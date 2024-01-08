package com.example.final_project.products.controller;

import com.example.final_project.products.entity.Products;
import com.example.final_project.products.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductsController {

    //RequiredArgsConstructor 를 사용할 땐 final를 꼭 넣도록 하자
    private final ProductsRepository productsRepository;

    @GetMapping("/products")
    public String list(Model model) {
        List<Products> productsList = this.productsRepository.findAll();
        model.addAttribute("productsList", productsList);
        return "products";
    }
}
