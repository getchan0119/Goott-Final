package com.example.final_project.products.service;

import com.example.final_project.products.entity.Products;
import com.example.final_project.products.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.final_project.products.Exception.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    //전체 위치 표시 (페이징 처리시 미사용 가능성 높음)
    public List<Products> getlist() {
        return this.productsRepository.findAll();
    }

    // 구매 버튼 누를 시 이동용
    public Products getProduct(Integer id) {
        Optional<Products> products = this.productsRepository.findById(id);
        if(products.isPresent()) {
            return products.get();
        } else {
            throw new DataNotFoundException("제품을 찾을 수 없음");
        }
    }
}
