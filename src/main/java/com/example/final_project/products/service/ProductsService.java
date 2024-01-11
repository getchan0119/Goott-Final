package com.example.final_project.products.service;

import com.example.final_project.products.entity.Products;
import com.example.final_project.products.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.final_project.products.Exception.DataNotFoundException;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ProductsService {
    @Value("${file.dir}")
    private String fileDir;

    private final ProductsRepository productsRepository;

    //전체 위치 표시 (페이징 처리시 미사용 가능성 높음)
    public Page<Products> getlist(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.asc("id"));
        Pageable pageable = PageRequest.of(page, 8 , Sort.by(sorts)); //페이지 8개 단위로끊음
        return this.productsRepository.findAll(pageable);
    }

    // 구매 버튼 누를 시 이동용 + 삭제 + Status 변경용
    public Products getProduct(Long id) {
        Optional<Products> products = this.productsRepository.findById(id);
        if(products.isPresent()) {
            return products.get();
        } else {
            throw new DataNotFoundException("제품을 찾을 수 없음");
        }
    }

    public Long saveProducts(String name, String explanation, Integer price, MultipartFile files) throws IOException {
        if (files.isEmpty()) {
            return null;
        }

        // 원래 파일 이름 추출
        String origName = files.getOriginalFilename();

        // 파일 이름으로 쓸 uuid 생성
        String uuid = UUID.randomUUID().toString();

        // 확장자 추출(ex : .png)
        String extension = origName.substring(origName.lastIndexOf("."));

        // uuid와 확장자 결합
        String savedName = uuid + extension;

        // 파일을 불러올 때 사용할 파일 경로
        String savedPath = fileDir + savedName;

        // 파일 엔티티 생성
        Products file = Products.builder()
                .name(name)
                .explanation(explanation)
                .price(price)
                .image(origName)
                .file_name(savedName)
                .file_path(savedPath)
                .status(0)
                .build();

        // 실제로 로컬에 uuid를 파일명으로 저장
        files.transferTo(new File(savedPath));

        // 데이터베이스에 파일 정보 저장
        Products savedFile = productsRepository.save(file);

        return savedFile.getId();
    }

    public Long modify(Long id, String name, String explanation, Integer price, MultipartFile files) throws IOException {

        //일단 기존 정보를 가져오자
        String origName = this.productsRepository.findById(id).get().getImage();
        String savedName = this.productsRepository.findById(id).get().getFile_name();
        String savedPath = this.productsRepository.findById(id).get().getFile_path();
        
        if(!files.isEmpty()) {
            // 원래 파일 이름 추출
            origName = files.getOriginalFilename();

            // 파일 이름으로 쓸 uuid 생성
            String uuid = UUID.randomUUID().toString();

            // 확장자 추출(ex : .png)
            String extension = origName.substring(origName.lastIndexOf("."));

            // uuid와 확장자 결합
            savedName = uuid + extension;

            // 파일을 불러올 때 사용할 파일 경로
            savedPath = fileDir + savedName;
        }

        // 파일 엔티티 생성
        Products file = Products.builder()
                .id(id)
                .name(name)
                .explanation(explanation)
                .price(price)
                .image(origName)
                .file_name(savedName)
                .file_path(savedPath)
                .status(0)
                .build();

        if(!files.isEmpty()) {
            // 실제로 로컬에 uuid를 파일명으로 저장
            files.transferTo(new File(savedPath));
        }

        // 데이터베이스에 파일 정보 저장
        Products savedFile = productsRepository.save(file);
        return savedFile.getId();
    }

    public void delete(Products products) {
        this.productsRepository.delete(products);
    }
}
