package com.example.final_project.products.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //인덱스

    private String name; //상품명

    @Column(columnDefinition = "TEXT")
    private String explanation; //상품 설명

    private Integer price; // 가격

    private Integer Status; // 0 : 판매중, 1 : 판매완료, 추후 옵션 추가 가능성 있음
}
