package com.example.final_project.products.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //인덱스

    private String name; //상품명

    @Column(columnDefinition = "TEXT")
    private String explanation; //상품 설명

    @Column(columnDefinition = "TEXT")
    private String image; // 사용자가 업로드한 파일 이름

    @Column(columnDefinition = "TEXT")
    private String file_name; // 변경된 이미지 이름

    @Column(columnDefinition = "TEXT")
    private String file_path; // 이미지 저장 위치

    private Integer price; // 가격

    private Integer Status; // 0 : 판매중, 1 : 판매완료, 추후 옵션 추가 가능성 있음

    @Builder
    public Products(Long id, String name, String explanation, Integer price, String image, String file_name, String file_path, Integer status) {
        this.id = id;
        this.name = name;
        this.explanation = explanation;
        this.price = price;
        this.image = image;
        this.file_name = file_name;
        this.file_path = file_path;
        this.Status = status;
    }

    public Products() {

    }
}
