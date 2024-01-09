package com.example.final_project.products.controller;

import com.example.final_project.products.entity.Products;
import com.example.final_project.products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductsController {

    //RequiredArgsConstructor 를 사용할 땐 final를 꼭 넣도록 하자
    private final ProductsService productsService;

    @GetMapping("/products/{id}")
    public String buy(Model model, @PathVariable("id") Long id) {
        Products products = this.productsService.getProduct(id);
        model.addAttribute("products_buy", products);
        return "products/products_buy";
    }

    @GetMapping("/products/create")
    public String create() {
        return "products/products_add";
    }

    @PostMapping("/products/create")
    public String create(
            Model model,
            @RequestParam(value="name") String name, @RequestParam(value="explanation") String explanation,
            @RequestParam(value = "price") Integer price, @RequestParam(value = "image") MultipartFile image) throws IOException {
        this.productsService.saveProducts(name, explanation, price, image);

        //리스트 출력용
        List<Products> productsList = this.productsService.getlist();
        model.addAttribute("productsList", productsList);
        return "/products/products";
    }
}
