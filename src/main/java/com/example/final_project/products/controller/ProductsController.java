package com.example.final_project.products.controller;

import com.example.final_project.products.entity.Products;
import com.example.final_project.products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductsController {

    //RequiredArgsConstructor 를 사용할 땐 final를 꼭 넣도록 하자
    private final ProductsService productsService;

    @GetMapping("/products/{id}")
    public String buy(Model model, @PathVariable("id") Integer id) {
        Products products = this.productsService.getProduct(id);
        model.addAttribute("products_buy", products);
        return "products/products_buy";
    }
}
