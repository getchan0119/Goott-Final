package com.example.final_project.Products;

import com.example.final_project.products.entity.Products;
import com.example.final_project.products.repository.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductsApplicationTests {

    @Autowired
    private ProductsRepository productsRepository;

    //To-Do : mysql로 연결중일 때 테스트하는 방법 찾아보기
}
